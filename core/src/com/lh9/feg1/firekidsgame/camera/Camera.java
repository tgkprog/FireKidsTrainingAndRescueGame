package com.lh9.feg1.firekidsgame.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class Camera extends OrthographicCamera {

	double destinationZoom;

	double zoomSpeed;

	Variables variables = new Variables();

	float accumulateMaxX = 3f;
	float accumulateMaxY = 3f;

	float accumulateDecreaseSpeedX = 13f;
	float accumulateIncreaseSpeedX = 5.4f;

	float accumulateDecreaseSpeedY = 13f;
	float accumulateIncreaseSpeedY = 5.4f;

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

	public void moveX(float dest, float acceleration, float decceleration,
			float accumulateMaxX) {
		destination.x = dest;
		accumulateDecreaseSpeedX = acceleration;
		accumulateIncreaseSpeedX = decceleration;
		this.accumulateMaxX = accumulateMaxX;
	}

	public void moveY(float dest, float acceleration, float decceleration,
			float accumulateMaxY) {
		destination.y = dest;
		accumulateDecreaseSpeedY = acceleration;
		accumulateIncreaseSpeedY = decceleration;
		this.accumulateMaxY = accumulateMaxY;
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
				if (zoom < destinationZoom)
					this.zoom += delta * 0.1f * zoomSpeed;
				if (zoom > destinationZoom)
					zoom -= delta * 0.1f * zoomSpeed;
				if (Math.abs(zoom - destinationZoom) < 0.005f) {
					zoom = (float) destinationZoom;
				}

			}
			updatePositionX(delta);
			updatePositionY(delta);
		}
	}

	public void changePosition(float x) {
		this.position.x = x;
		this.update();
	}

	public void zoom(float destinationZoom, double zoomSpeed) {
		this.destinationZoom = destinationZoom;
		this.zoomSpeed = zoomSpeed;
	}

	void updatePositionX(float delta) {
		if (this.position.x < destination.x) {

			this.position.x += accumulatePlusX;

			if (Math.abs(this.position.x - destination.x) <= 100) {
				accumulatePlusX -= delta * accumulateDecreaseSpeedX
						* accumulatePlusX;
			} else
				accumulatePlusX += delta * accumulateIncreaseSpeedX
						* accumulatePlusX;

			if (this.position.x > destination.x)
				this.position.x = destination.x;
		}

		if (this.position.x > destination.x) {
			this.position.x += accumulateMinusX;

			if (Math.abs(this.position.x - destination.x) <= 100) {
				accumulateMinusX += delta * accumulateDecreaseSpeedX
						* Math.abs(accumulateMinusX);
			} else
				accumulateMinusX -= delta * accumulateIncreaseSpeedX
						* Math.abs(accumulateMinusX);

			if (this.position.x < destination.x)
				this.position.x = destination.x;
		}

		if (this.position.x == destination.x) {
			accumulatePlusX = accumulateIncreaseSpeedX;
			accumulateMinusX = -accumulateIncreaseSpeedX;
		}

		if (accumulatePlusX > accumulateMaxX)
			accumulatePlusX = accumulateMaxX;
		if (accumulateMinusX < -accumulateMaxX)
			accumulateMinusX = -accumulateMaxX;

	}

	void updatePositionY(float delta) {

		if (this.position.y < destination.y) {

			this.position.y += accumulatePlusY;

			if (Math.abs(this.position.y - destination.y) <= 100) {
				accumulatePlusY -= delta * accumulateDecreaseSpeedY
						* accumulatePlusY;
			} else
				accumulatePlusY += delta * accumulateIncreaseSpeedY
						* accumulatePlusY;

			if (this.position.y > destination.y)
				this.position.y = destination.y;
		}

		if (this.position.y > destination.y) {
			this.position.y += accumulateMinusY;

			if (Math.abs(this.position.y - destination.y) <= 100) {
				accumulateMinusY += delta * accumulateDecreaseSpeedY
						* Math.abs(accumulateMinusY);
			} else
				accumulateMinusY -= delta * accumulateIncreaseSpeedY
						* Math.abs(accumulateMinusX);

			if (this.position.y < destination.y)
				this.position.y = destination.y;
		}

		if (this.position.y == destination.y) {
			accumulatePlusY = accumulateIncreaseSpeedY;
			accumulateMinusY = -accumulateIncreaseSpeedY;
		}

		if (accumulatePlusY > accumulateMaxY)
			accumulatePlusY = accumulateMaxY;
		if (accumulateMinusY < -accumulateMaxY)
			accumulateMinusY = -accumulateMaxY;
	}
}