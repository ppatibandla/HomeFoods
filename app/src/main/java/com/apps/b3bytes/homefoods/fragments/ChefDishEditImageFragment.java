package com.apps.b3bytes.homefoods.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.apps.b3bytes.homefoods.R;
import com.apps.b3bytes.homefoods.activities.HomePage;
import com.apps.b3bytes.homefoods.models.DishOnSale;

import java.io.File;

public class ChefDishEditImageFragment extends Fragment {
    private FragmentActivity mContext;
    private View rootView;
    private DishOnSale mDish;
    private int mMode;

    private ImageView ivDishEditDishImage;
    private EditText etDishAdditionalInfo;
    private LinearLayout llDishImageNavigationButtons;
    private Button bDishImageSave;
    private Button bDishImageBack;
    private LinearLayout llDishImageSaveButtons;
    private Button bDishImageSaveOnly;

    OnDishImageSaveSelectedListener mSaveCallback;
    OnDishImageBackSelectedListener mBackCallback;

    private Uri outputFileUri;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;

    // Container Activity must implement this interface
    public interface OnDishImageSaveSelectedListener {
        public void onDishImageSaveSelected(DishOnSale mDish, int mode);
    }

    // Container Activity must implement this interface
    public interface OnDishImageBackSelectedListener {
        public void onDishImageBackSelected(DishOnSale mDish);
    }


    @Override
    public void onAttach(Activity activity) {
        mContext = (FragmentActivity) activity;

        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mSaveCallback = (OnDishImageSaveSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDishImageSaveSelectedListener");
        }

        try {
            mBackCallback = (OnDishImageBackSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDishImageBackSelectedListener");
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mDish = (DishOnSale) bundle.getParcelable("dish");
            mMode = bundle.getInt("mode", HomePage.DISH_SECTION_EDIT_ALL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_chef_dish_edit_image, container, false);
        ivDishEditDishImage = (ImageView) rootView.findViewById(R.id.ivDishEditDishImage);
        etDishAdditionalInfo = (EditText) rootView.findViewById(R.id.etDishAdditionalInfo);
        llDishImageNavigationButtons = (LinearLayout) rootView.findViewById(R.id.llDishImageNavigationButtons);
        bDishImageSave = (Button) rootView.findViewById(R.id.bDishImageSave);
        bDishImageBack = (Button) rootView.findViewById(R.id.bDishImageBack);
        llDishImageSaveButtons = (LinearLayout) rootView.findViewById(R.id.llDishImageSaveButtons);
        bDishImageSaveOnly = (Button) rootView.findViewById(R.id.bDishImageSaveOnly);

        initFields();
        return rootView;
    }

    private void initEditTextView(EditText etView, String text) {
        if (text != null && !text.isEmpty()) {
            etView.setText(text);
        }
    }

    private void initFields() {
        //populate  fields if applicable. i.e. mDish != null
        if (mDish != null) {
            //TODO: add image
            initEditTextView(etDishAdditionalInfo, mDish.getmDishAddInfo());
        }

        if (mMode == HomePage.DISH_SECTION_EDIT_ALL) {
            llDishImageNavigationButtons.setVisibility(View.VISIBLE);
            llDishImageSaveButtons.setVisibility(View.GONE);
        } else if (mMode == HomePage.DISH_SECTION_EDIT_SINGLE) {
            llDishImageSaveButtons.setVisibility(View.VISIBLE);
            llDishImageNavigationButtons.setVisibility(View.GONE);
        }

    }

    private void readFields() {
        if (mDish != null) {
            // update image url
            mDish.setmDishAddInfo(etDishAdditionalInfo.getText().toString());
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivDishEditDishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://stackoverflow.com/questions/11732872/android-how-can-i-call-camera-or-gallery-intent-together
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
                myAlertDialog.setTitle("Upload Pictures Option");
                myAlertDialog.setMessage("How do you want to set your picture?");

                myAlertDialog.setPositiveButton("Gallery",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                pictureActionIntent = new Intent(
                                        Intent.ACTION_GET_CONTENT, null);
                                pictureActionIntent.setType("image/*");
                                pictureActionIntent.putExtra("return-data", true);
                                startActivityForResult(pictureActionIntent,
                                        GALLERY_PICTURE);
                            }
                        });

                myAlertDialog.setNegativeButton("Camera",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
                                root.mkdirs();
                                final String fname = "img_" + System.currentTimeMillis() + ".jpg"; //Utils.getUniqueImageFilename();
                                final File sdImageMainDirectory = new File(root, fname);
                                outputFileUri = Uri.fromFile(sdImageMainDirectory);
                                pictureActionIntent = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                pictureActionIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                                startActivityForResult(pictureActionIntent,
                                        CAMERA_REQUEST);

                            }
                        });
                myAlertDialog.show();
            }
        });

        bDishImageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFields();
                mSaveCallback.onDishImageSaveSelected(mDish, mMode);
            }
        });

        bDishImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFields();
                mBackCallback.onDishImageBackSelected(mDish);
            }
        });

        bDishImageSaveOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFields();
                mSaveCallback.onDishImageSaveSelected(mDish, mMode);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if ((requestCode == GALLERY_PICTURE) || (requestCode == CAMERA_REQUEST)) {
                boolean isCamera = false;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
                    } else if (requestCode == CAMERA_REQUEST) {
                        isCamera = action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                }

                Uri selectedImageUri;
                if (isCamera) {
                    selectedImageUri = outputFileUri;
                } else {
                    selectedImageUri = data == null ? null : data.getData();
                }
                if (selectedImageUri != null) {
                    ivDishEditDishImage.setImageURI(null);
                    ivDishEditDishImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

}
