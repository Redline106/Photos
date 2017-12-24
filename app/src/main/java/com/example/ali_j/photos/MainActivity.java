package com.example.ali_j.photos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageButton;
import  android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;


public class MainActivity extends AppCompatActivity {
    private ImageView vuePhoto;
    private ImageButton buttonCamera;
    private EditText titrePhoto;
    private String nomPhoto;
    static final int CAM_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vuePhoto=(ImageView)findViewById(R.id.vuePhoto);
        titrePhoto=(EditText) findViewById(R.id.titrePhoto);
        buttonCamera=(ImageButton)findViewById(R.id.boutonCamera);


    }




    private File creerFichier(){

        File monDossier=new File("DCIM/monDossier");

        if (!monDossier.exists()){
            monDossier.mkdir();

        }
        File maPhoto=new File(monDossier,nomPhoto);

        return maPhoto;

    }


    public  void PrendrePhoto(View view ){
        String texte =titrePhoto.getText().toString();
        if(texte!=null&& !texte.isEmpty()){
            nomPhoto=texte+".jpg";
            Intent maCamera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File Photo=creerFichier();

            maCamera.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(Photo));
            startActivityForResult(maCamera,CAM_REQUEST);


        }else {

            Toast.makeText(this,"Vous devez saisir le titre de photo",Toast.LENGTH_LONG).show();


        }
    }
@Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        String cheminFichier ="DCIM/monDossier/"+nomPhoto;

        vuePhoto.setImageDrawable(Drawable.createFromPath(cheminFichier));

        titrePhoto.setText("");

    }

}
