package com.android.launcher2;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class WallpaperFullscreen {

	public static Bitmap changBitmap(Bitmap b, int w, int h)
    {
    	int width = b.getWidth();
    	int height = b.getHeight();
    	float scaleWidth = ((float)w)/width;
    	float scaleHeight = ((float)h)/height;
    	Matrix matrix = new Matrix();
    	matrix.postScale(scaleWidth, scaleHeight);
    	return Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);
    }
	
	public static void RefreshWallpaper(Activity context)
	{
		try{
		WallpaperManager wpm = (WallpaperManager) context.getSystemService(
                Context.WALLPAPER_SERVICE);
        int desiredWidth = context.getWindowManager().getDefaultDisplay().getWidth();
        int desiredHeight = context.getWindowManager().getDefaultDisplay().getHeight();
        Drawable wallpaperDrawable = wpm.getDrawable();
//        Bitmap oldBitmap = wpm.getBitmap();
        Bitmap oldBitmap = ((BitmapDrawable)wallpaperDrawable).getBitmap();
        Bitmap newBitmap = changBitmap(oldBitmap, desiredWidth, desiredHeight);
        wpm.suggestDesiredDimensions(desiredWidth, desiredHeight);       
//        wpm.setBitmap(newBitmap);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}    
	}
	
	public static void SetNewWallpaper(Activity context, Bitmap bitmap)
	{
		try{
			WallpaperManager wpm = (WallpaperManager) context.getSystemService(
	                Context.WALLPAPER_SERVICE);
	        int desiredWidth = context.getWindowManager().getDefaultDisplay().getWidth();
	        int desiredHeight = context.getWindowManager().getDefaultDisplay().getHeight();
	        Bitmap newBitmap = changBitmap(bitmap, desiredWidth, desiredHeight);
	        wpm.suggestDesiredDimensions(desiredWidth, desiredHeight);
	        wpm.setBitmap(newBitmap);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
}
