package br.com.lucasfsilva.exemplonotificationpicturemediastyle;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static br.com.lucasfsilva.exemplonotificationpicturemediastyle.App.CHANNEL_ID_01;
import static br.com.lucasfsilva.exemplonotificationpicturemediastyle.App.CHANNEL_ID_02;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private EditText edtTitulo, edtMensagem;

    private MediaSessionCompat mediaSessionCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtMensagem = (EditText) findViewById(R.id.edtMensagem);

        mediaSessionCompat = new MediaSessionCompat(this, "tag");
    }

    public void enviarNoChannel01(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.agua);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_01)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void enviarNoChannel02(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();

        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.agua);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_02)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_dislike_01, "Dislike", null)
                .addAction(R.drawable.ic_back_01, "Antes", null)
                .addAction(R.drawable.ic_pause_01, "Pausa", null)
                .addAction(R.drawable.ic_next_01, "Próximo", null)
                .addAction(R.drawable.ic_like_01, "Like", null)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setSubText("Sub texto")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}

