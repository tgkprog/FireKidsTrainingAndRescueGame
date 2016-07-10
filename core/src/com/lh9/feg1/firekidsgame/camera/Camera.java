package com.lh9.feg1.firekidsgame.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class Camera extends OrthographicCamera {

	double destinationZoomOut;
	double destinationZoomIn;
	Variables variables = new Variables();

	static final float accumulateMax = 50;
	float accumulateDecreaseSpeed = 13f;
	float accumulateIncreaseSpeed = 5.4f;

	Vector2 destination;
	float accumulatePlusX = 1f;
	float accumulateMinusX = -1f;

	float accumulatePlusY = 1f;
	float accumulateMinusY = -1f;

	float timer = 0;

	boolean screenShake;
	double screenShakeTimer;
	double screenShakeFrequency;
	double previousZoom;

	void manageShakingScreen(float delta) {
		if (screenShake == true) {

			screenShakeFrequency += delta;
			screenShakeTimer += delta;

			if (screenShakeFrequency > 0.05f) {
				screenShakeFrequency = 0;
				zoom = MathUtils.random(0.99f, 1f);
				update();
			}
			if (screenShakeTimer >= 0.3f) {
				screenShake = false;
				zoom = (float) previousZoom;
			}
		}
	}

	public void shakeScreen() {
		screenShake = true;
		previousZoom = zoom;
		screenShakeTimer = 0;
		screenShakeFrequency = 0;
	}

	public Camera(float viewportWidth, float viewportHeight) {
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
		this.near = 0;
		update();
		destination = new Vector2();
		destination.x = 400;
	}

	public void moveX(float dest, float acceleration, float decceleration) {
		destination.x = dest;
		accumulateDecreaseSpeed = acceleration;
		accumulateIncreaseSpeed = decceleration;
	}

	public void moveY(float dest, float acceleration, float decceleration) {
		destination.y = dest;
		accumulateDecreaseSpeed = acceleration;
		accumulateIncreaseSpeed = decceleration;

	}

	public void update(double delta) {
		super.update();
		updateTimers((float) delta);
		manageShakingScreen((float) delta);
	}

	public void updateTimers(float delta) {

		timer += delta;
		if (timer > 0.01f) {
			timer = 0;
			if (screenShake == false) {
				if (zoom < destinationZoomOut)
					this.zoom += delta * 0.1f;
				if (zoom > destinationZoomOut)
					zoom = (float) destinationZoomOut;
			}
			updatePositionX(delta);
			updatePositionY(delta);
		}
	}

	public void changePosition(float x) {
		this.position.x = x;
		this.update();
	}

	public void zoomOut(float destinationZoomOut) {
		this.destinationZoomOut = destinationZoomOut;
	}

	void updatePositionX(float delta) {
		if (this.position.x < destination.x) {

			this.position.x += accumulatePlusX;

			if (Math.abs(this.position.x - destination.x) <= 100) {
				accumulatePlusX -= delta * accumulateDecreaseSpeed
						* accumulatePlusX;
			} else
				accumulatePlusX += delta * accumulateIncreaseSpeed
						* accumulatePlusX;

			if (this.position.x > destination.x)
				this.position.x = destination.x;
		}

		if (this.position.x > destination.x) {
			this.position.x += accumulateMinusX;

			if (Math.abs(this.position.x - destination.x) <= 100) {
				accumulateMinusX += delta * accumulateDecreaseSpeed
						* Math.abs(accumulateMinusX);
			} else
				accumulateMinusX -= delta * accumulateIncreaseSpeed
						* Math.abs(accumulateMinusX);

			if (this.position.x < destination.x)
				this.position.x = destination.x;
		}

		if (this.position.x == destination.x) {
			accumulatePlusX = accumulateIncreaseSpeed;
			accumulateMinusX = -accumulateIncreaseSpeed;
		}

		if (accumulatePlusX > accumulateMax)
			accumulatePlusX = accumulateMax;
		if (accumulateMinusX < -accumulateMax)
			accumulateMinusX = -accumulateMax;

	}

	void updatePositionY(float delta) {
		if (this.position.y < destination.y) {

			this.position.y += accumulatePlusY;

			if (Math.abs(this.position.y - destination.y) <= 100) {
				accumulatePlusY -= delta * accumulateDecreaseSpeed
						* accumulatePlusY;
			} else
				accumulatePlusY += delta * accumulateIncreaseSpeed
						* accumulatePlusY;

			if (this.position.y > destination.y)
				this.position.y = destination.y;
		}

		if (this.position.y > destination.y) {
			this.position.y += accumulateMinusX;

			if (Math.abs(this.position.y - destination.y) <= 100) {
				accumulateMinusY += delta * accumulateDecreaseSpeed
						* Math.abs(accumulateMinusX);
			} else
				accumulateMinusY -= delta * accumulateIncreaseSpeed
						* Math.abs(accumulateMinusX);

			if (this.position.y < destination.y)
				this.position.y = destination.y;
		}

		if (this.position.y == destination.y) {
			accumulatePlusY = accumulateIncreaseSpeed;
			accumulateMinusY = -accumulateIncreaseSpeed;
		}

		if (accumulatePlusX > accumulateMax)
			accumulatePlusX = accumulateMax;
		if (accumulateMinusX < -accumulateMax)
			accumulateMinusX = -accumulateMax;

	}

}
