package com.lh9.feg1.firekidsgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;

public class InputInterpreter implements GestureListener {

	Human controlledHuman;
	Truck controlledTruck;

	String selectedScreen = "No button clicked";

	CloudManager cloudManager;
	Dialogue dialogueWindow;

	Camera camera;
	OrthographicCamera guiCamera;

	Button up;
	Button down;

	Button fireStation;
	Button pause;
	Button meetTheTrucks;
	Button levelButtons[];
	Button runButton;
	Button runButtonSecond;
	Button yellowSectionMiddle;
	Button yellowSectionLeft;
	Button yellowSectionUpLeft;
	Button yellowSectionUpRight;
	Button eclipseFire;
	
	Variables variables = new Variables();

	boolean wasPannedBefore;
	boolean assetsLoaded;
	boolean initialCameraMovements;

	double initialPanX;
	double initialPanY;

	Vector3 xyzTap = new Vector3();
	double tapX;
	double tapY;

	double panX = 0;
	double panY = 0;
	double touchDownX = 0;
	double touchDownY = 0;
	double zoomDelta;

	public String message = "No data yet";
	public boolean touched;
	public boolean panned;
	public boolean touchedDown;
	boolean justStoppedPanning;

	public boolean zoomDeltaChanged;

	public double getZoomDelta() {
		if (zoomDeltaChanged == true) {
			zoomDeltaChanged = false;
			return zoomDelta / 100;
		} else
			return 0;
	}

	public double getPanX() {
		return panX;
	}

	public double getPanY() {
		return panY;
	}

	public boolean getPanned() {
		return panned;
	}

	public double getTouchDownX() {
		return touchDownX;
	}

	public double getTouchDownY() {
		return touchDownY;
	}

	public boolean getTouchDown() {
		if (touchedDown == true) {
			touchedDown = false;
			return true;
		} else
			return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		touchedDown = true;
		touchDownX = x;
		touchDownY = y;

		if (variables.getDebugMode() == true)
			System.out.println("touchDown");

		Vector3 vec = new Vector3();
		vec.x = x;
		vec.y = y;

		tapX = x;
		tapY = y;

		guiCamera.unproject(vec);
		manageButtonsCollisions(vec.x, vec.y);
		manageDialogues(x, y);


		vec = new Vector3();
		vec.x = x;
		vec.y = y;
		camera.unproject(vec);
		
		if(eclipseFire != null)
		if (eclipseFire.checkCollision((int) vec.x, (int) vec.y) == true) {
			eclipseFire.blink();
		}
		
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub

		if (variables.getDebugMode() == true)
			System.out.println("tap");
		touched = true;

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		if (variables.getDebugMode() == true)
			System.out.println("longPress");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Math.abs(velocityX) > Math.abs(velocityY) && assetsLoaded == true) {
			if (velocityX > 0) {

			} else if (velocityX < 0) {

			} else {
				// Do nothing.
			}
		} else {

			// Ignore the input, because we don't care about up/down swipes.
		}
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		panned = true;
		panX = x;
		panY = y;

		if (variables.getDebugMode() == true)
			System.out.println("pan");

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub

		if (variables.getDebugMode() == true)
			System.out.println("panStop");
		panned = false;
		justStoppedPanning = true;

		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {

		if (variables.getDebugMode() == true) {
			System.out.println("zoom");

			System.out.println("initialDistance: " + initialDistance
					+ "distance: " + distance);
			message = "initialDistance: " + initialDistance + "distance: "
					+ distance;
		}
		if (initialDistance > distance)
			zoomDelta = initialDistance / distance;
		else if (initialDistance < distance)
			zoomDelta = distance / initialDistance;
		if (distance > initialDistance) {
			zoomDelta *= (-1);
		}
		zoomDeltaChanged = true;

		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub

		if (variables.getDebugMode() == true)
			System.out.println("pinch");
		return false;
	}

	public InputInterpreter() {
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	public Vector3 getLastTouchPosition() {
		touched = false;
		xyzTap.x = (float) tapX;
		xyzTap.y = (float) tapY;
		return xyzTap;
	}

	public boolean justStoppedPanning() {
		if (justStoppedPanning == true) {
			justStoppedPanning = false;
			return true;
		} else
			return false;

	}

	void manageButtonsCollisions(double x, double y) {
		if (cloudManager.getAllScalesEqualZero() == true) {

			if (yellowSectionUpRight != null) {

				if (yellowSectionMiddle.checkCollision((int) x, (int) y) == true) {
					yellowSectionMiddle.blink();
				}
				if (yellowSectionLeft.checkCollision((int) x, (int) y) == true) {

					yellowSectionLeft.blink();
				}
				if (yellowSectionUpLeft.checkCollision((int) x, (int) y) == true) {

					yellowSectionUpLeft.blink();
				}
				if (yellowSectionUpRight.checkCollision((int) x, (int) y) == true) {

					yellowSectionUpRight.blink();
				}

			}

			if (up != null) {
				if (up.checkCollision((int) x, (int) y) == true) {
					controlledTruck.upLane();
					up.blink();
				}
			}
			if (down != null) {
				if (down.checkCollision((int) x, (int) y) == true) {
					down.blink();
					controlledTruck.downLane();
				}
			}
			if (runButton != null)
				if (runButton.checkCollision((int) x, (int) y) == true) {
					runButton.blink();
					controlledHuman.move();

					if (camera.zoom == 3.0f)
						camera.zoom(2.96f, 3);
					if (camera.zoom <= 2.96f && camera.zoom >= 2.9f)
						camera.zoom(3.0f, 3);

					if (camera.zoom == 0.98f)
						camera.zoom(0.96f, 1);
					if (camera.zoom <= 0.96f)
						camera.zoom(0.98f, 1);

					if (runButton.dontRespond == true) {
						camera.zoom(0.98f, 1);
					}
				}

			if (runButtonSecond != null)
				if (runButtonSecond.checkCollision((int) x, (int) y) == true) {
					runButtonSecond.blink();
					controlledHuman.moveReverse();

					if (camera.zoom == 3.0f)
						camera.zoom(2.96f, 3);
					if (camera.zoom <= 2.96f && camera.zoom >= 2.9f)
						camera.zoom(3.0f, 3);

					if (camera.zoom == 0.98f)
						camera.zoom(0.96f, 1);
					if (camera.zoom <= 0.96f)
						camera.zoom(0.98f, 1);

					if (runButtonSecond.dontRespond == true) {
						camera.zoom(0.98f, 1);
					}
				}

			if (pause != null)
				if (pause.checkCollision((int) x, (int) y) == true) {
					pause.blink();
				}
			if (meetTheTrucks != null) {
				if (fireStation.checkCollision((int) x, (int) y) == true) {
					fireStation.blink();
				}
				if (meetTheTrucks.checkCollision((int) x, (int) y) == true) {
					meetTheTrucks.blink();
					selectedScreen = variables.getMeetTheTrucks();
					cloudManager.start();
				}

				for (int a = 0; a < 7; a++) {
					if (levelButtons[a].checkCollision((int) x, (int) y) == true) {
						levelButtons[a].blink();

						if (a == 0) {
							selectedScreen = variables.getFitnessScreenOne();
							cloudManager.start();
						}
						if (a == 1) {
							selectedScreen = variables.getTrainingScreen();
							cloudManager.start();
						}

						if (a == 2) {
							selectedScreen = variables.getTrainingScreenTwo();
							cloudManager.start();
						}
						if (a == 4) {
							selectedScreen = variables.getRescueMetroScreen();
							cloudManager.start();
						}
						if (a == 6) {
							selectedScreen = variables.getBigRoadRescueScreen();
							cloudManager.start();
						}
					}
				}

			}
		}
	}

	public void setLevelButtons(Button[] levelButtons) {
		this.levelButtons = levelButtons;
	}

	public void setMeetTheTrucks(Button meetTheTrucks) {
		this.meetTheTrucks = meetTheTrucks;
	}

	public void setCameras(Camera camera, OrthographicCamera guiCamera) {
		this.camera = camera;
		this.guiCamera = guiCamera;
	}

	public void setCloudManager(CloudManager cloudManager) {
		this.cloudManager = cloudManager;
	}

	public void setPauseButton(Button pause) {
		this.pause = pause;
	}

	public String getSelectedScreenName() {
		return selectedScreen;
	}

	@Override
	public void pinchStop() {
		// TODO Auto-generated method stub

	}

	public void setFireStation(Button fireStation) {
		this.fireStation = fireStation;
	}

	public void setDialogueWindow(Dialogue dialogueWindow) {
		this.dialogueWindow = dialogueWindow;
	}

	void manageDialogues(float x, float y) {
		if (dialogueWindow != null)
			if (dialogueWindow.isVisibile() == true) {
				dialogueWindow.hide();
			}
	}

	public void setRunButton(Button runButton) {
		this.runButton = runButton;
	}
	public void setEclipseFire(Button eclipseFire) {
		this.eclipseFire = eclipseFire;
	}
	public void setRunButtonSecond(Button runButtonSecond) {
		this.runButtonSecond = runButtonSecond;
	}
	public void setControlledHuman(Human controlledHuman) {
		this.controlledHuman = controlledHuman;
	}

	public void setControlledTruck(Truck controlledTruck) {
		this.controlledTruck = controlledTruck;
	}

	public void loadUp(Button up) {
		this.up = up;
	}

	public void loadDown(Button down) {
		this.down = down;
	}

	public void setYellowSectionButtons(Button yellowSectionMiddle,
			Button yellowSectionLeft, Button yellowSectionUpLeft,
			Button yellowSectionUpRight) {
		this.yellowSectionMiddle = yellowSectionMiddle;
		this.yellowSectionLeft = yellowSectionLeft;
		this.yellowSectionUpLeft = yellowSectionUpLeft;
		this.yellowSectionUpRight = yellowSectionUpRight;
	}
}
/*
 * touchDown: A user touches the screen.
 * 
 * longPress: A user touches the screen for some time.
 * 
 * tap: A user touches the screen and lifts the finger again. The finger must
 * not move outside a specified square area around the initial touch position
 * for a tap to be registered. Multiple consecutive taps will be detected if the
 * user performs taps within a specified time interval.
 * 
 * pan: A user drags a finger across the screen. The detector will report the
 * current touch coordinates as well as the delta between the current and
 * previous touch positions. Useful to implement camera panning in 2D.
 * 
 * panStop: Called when no longer panning.
 * 
 * fling: A user dragged the finger across the screen, then lifted it. Useful to
 * implement swipe gestures.
 * 
 * zoom: A user places two fingers on the screen and moves them together/apart.
 * The detector will report both the initial and current distance between
 * fingers in pixels. Useful to implement camera zooming.
 * 
 * pinch: Similar to zoom. The detector will report the initial and current
 * finger positions instead of the distance. Useful to implement camera zooming
 * and more sophisticated gestures such as rotation.
 * 
 * From: https://github.com/libgdx/libgdx/wiki/Gesture-detection
 */