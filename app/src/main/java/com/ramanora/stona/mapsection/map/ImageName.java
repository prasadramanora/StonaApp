package com.ramanora.stona.mapsection.map;

//import com.theappguruz.R;
import com.ramanora.stona.R;

import java.util.ArrayList;
import java.util.Random;

public class ImageName {

	private Random randNo;
	private ArrayList<Integer> imageName;

	public ImageName() {
		imageName = new ArrayList<Integer>();
		imageName.add(R.mipmap.ic_launcher);

	}

	public int getImageId() {
		randNo = new Random();
		return imageName.get(randNo.nextInt(imageName.size()));
	}
}
