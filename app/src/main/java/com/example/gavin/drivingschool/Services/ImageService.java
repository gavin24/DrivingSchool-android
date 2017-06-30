package com.example.gavin.drivingschool.Services;

import com.example.gavin.drivingschool.Domain.Image;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface ImageService {
    Image addImage(Image animal);
    Image updateImage( Image animal);
    Image deleteImage( Image animal);
    Image getImage(Long d);
    ArrayList<Image> getAllImages( );

    void removeAllImages();
}
