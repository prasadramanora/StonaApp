package com.ramanora.stona.bean;

public class CameraPojo {

	// private variables
	public int _id;
	public byte[] _image;

	// Empty constructor
	public CameraPojo() {

	}

	// constructor
	public CameraPojo(int keyId, byte[] image) {
		this._id = keyId;
		this._image = image;

	}
	public CameraPojo(byte[] image) {

		this._image = image;

	}
	public CameraPojo(int keyId) {
		this._id = keyId;

	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int keyId) {
		this._id = keyId;
	}

	// getting phone number
	public byte[] getImage() {
		return this._image;
	}

	// setting phone number
	public void setImage(byte[] image) {
		this._image = image;
	}
}
