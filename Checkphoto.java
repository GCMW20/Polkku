<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/checkphoto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ffe5b2 "
    android:orientation="vertical">

    <ImageView
        android:id="@+id/frame"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginStart="53dp"
        android:layout_marginLeft="53dp"
        android:layout_marginTop="80dp"
        android:layout_marginEnd="53dp"
        android:layout_marginRight="53dp"
        android:layout_marginBottom="54dp"
        android:background="@drawable/frame"
        app:layout_constraintBottom_toTopOf="@+id/button2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginStart="69dp"
        android:layout_marginLeft="69dp"
        android:layout_marginTop="104dp"
        android:layout_marginEnd="69dp"
        android:layout_marginRight="69dp"
        android:layout_marginBottom="139dp"
        android:background="@drawable/black"
        app:layout_constraintBottom_toTopOf="@+id/button1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button1"
        android:layout_width="110dp"
        android:layout_height="52dp"
        android:layout_marginEnd="41dp"
        android:layout_marginRight="41dp"
        android:layout_marginBottom="71dp"
        android:background="#3871a6"
        android:text="CAMERA"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/button2"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />

    <Button
        android:id="@+id/button2"
        android:layout_width="110dp"
        android:layout_height="52dp"
        android:layout_marginStart="46dp"
        android:layout_marginLeft="46dp"
        android:layout_marginEnd="44dp"
        android:layout_marginRight="44dp"
        android:layout_marginBottom="71dp"
        android:background="#3871a6"
        android:text="GALLERY"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/button1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/frame" />

</androidx.constraintlayout.widget.ConstraintLayout>
