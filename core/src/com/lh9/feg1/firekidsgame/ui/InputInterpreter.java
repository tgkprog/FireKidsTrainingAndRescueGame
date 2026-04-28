package com.lh9.feg1.firekidsgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

import static com.lh9.feg1.firekidsgame.screens.MenuScreen.game;

public class InputInterpreter implements GestureListener {

    Button generalPurposeButton;

    MyTextInputListener textInputListener;
    int selectedUserInputID = -1;
    Button[] userInputButtons;
    AssetsManager assetsManager;
    boolean[] screensPlayed;
    String selectedScreen = "No button clicked";
    Button webButton;
    Button jump;
    Button gender;
    Button[] hitboxes;
    Button userScreenButton;
    Button unlockGameButton;
    DataOrganizer dataOrganizer;
    MenuWindow menuWindow;
    Button[] settingsButtons;
    Button retryButton;
    Button playButton;
    Human controlledHuman;
    Truck controlledTruck;
    CloudManager cloudManager;
    Dialogue dialogueWindow;
    Camera camera;
    OrthographicCamera guiCamera;
    Button authors;
    Button up;
    Button down;
    Button menu;
    Button settings;
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
    Vector3 xyzTap = new Vector3();
    String message = "No data yet";

    boolean wasPannedBefore;
    boolean assetsLoaded;
    boolean initialCameraMovements;
    boolean touched;
    boolean panned;
    boolean touchedDown;
    boolean justStoppedPanning;
    boolean zoomDeltaChanged;

    double initialPanX;
    double initialPanY;
    double tapX;
    double tapY;
    double panX = 0;
    double panY = 0;
    double touchDownX = 0;
    double touchDownY = 0;
    double zoomDelta;

    public InputInterpreter() {
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

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

        if (variables.getDEBUG_MODE() == true)
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

        manageNonGuiCollisions(vec.x, vec.y);

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        // TODO Auto-generated method stub

        if (variables.getDEBUG_MODE() == true)
            System.out.println("tap");
        touched = true;

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        // TODO Auto-generated method stub
        if (variables.getDEBUG_MODE() == true)
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

        if (variables.getDEBUG_MODE() == true)
            System.out.println("pan");

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        // TODO Auto-generated method stub

        if (variables.getDEBUG_MODE() == true)
            System.out.println("panStop");
        panned = false;
        justStoppedPanning = true;

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        if (variables.getDEBUG_MODE() == true) {
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

        if (variables.getDEBUG_MODE() == true)
            System.out.println("pinch");
        return false;
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
            if (gender != null) {
                if (gender.checkCollision((int) x, (int) y) == true) {
                    gender.blink();
                    dataOrganizer.setGender(!dataOrganizer.getGender());
                }
            }

            if (userInputButtons != null) {

                if (userInputButtons[userInputButtons.length - 1].checkCollision((int) x, (int) y) == true) {
                    userInputButtons[userInputButtons.length - 1].blink();
                }

                boolean anyCollision = false;
                for (int a = 0; a < userInputButtons.length - 1; a++) {
                    if (userInputButtons[a].checkCollision((int) x, (int) y) == true) {
                        textInputListener.start();
                        userInputButtons[a].blink();
                        for (int b = 0; b < userInputButtons.length; b++) {
                            userInputButtons[b].normal();
                        }
                        userInputButtons[a].red();
                        anyCollision = true;
                        selectedUserInputID = a;
                    }
                }
                if (anyCollision == false) {
                    //	selectedUserInputID = -1;
                    for (int b = 0; b < userInputButtons.length; b++) {
                        userInputButtons[b].normal();
                    }
                }
            }
            if (generalPurposeButton != null) {
                if (generalPurposeButton.checkCollision((int) x, (int) y) == true) {
                    generalPurposeButton.blink();

                }
            }
            if (userScreenButton != null) {
                if (userScreenButton.checkCollision((int) x, (int) y) == true) {
                    userScreenButton.blink();
                    cloudManager.start();
                    selectedScreen = Variables.USER_INPUT_SCREEN;
                }
            }
            if (unlockGameButton != null) {
                if (unlockGameButton.checkCollision((int) x, (int) y) == true) {
                }
            }
            if (settingsButtons != null) {
                if (settingsButtons[0].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[0].blink();
                    dataOrganizer.setFps(!dataOrganizer.getFps());
                }
                if (settingsButtons[1].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[1].blink();
                    dataOrganizer.setTextureFiltering(!dataOrganizer
                            .getTextureFiltering());
                }
                if (settingsButtons[2].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[2].blink();
                    dataOrganizer.setVoice(!dataOrganizer.getVoice());
                }
                if (settingsButtons[3].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[3].blink();
                    dataOrganizer.setVibrations(!dataOrganizer.getVibrations());
                }
                if (settingsButtons[4].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[4].blink();
                    dataOrganizer.setScreenAwake(!dataOrganizer
                            .getScreenAwake());
                }
                if (settingsButtons[5].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[5].blink();
                    dataOrganizer.setPrompts(!dataOrganizer.getPrompts());
                }
                if (settingsButtons[6].checkCollision((int) x, (int) y) == true) {
                    settingsButtons[6].blink();
                    dataOrganizer.resetGameState();
                }

            }
            if (webButton != null) {
                if (webButton.checkCollision((int) x, (int) y) == true) {
                    webButtonAction();
                }
            }
            if (retryButton != null) {
                if (retryButton.checkCollision((int) x, (int) y) == true) {
                    retryButton.blink();
                    selectedScreen = menuWindow.getScreen();
                    cloudManager.start();
                }
            }
            if (playButton != null) {
                if (playButton.checkCollision((int) x, (int) y) == true) {
                    playButton.blink();
                    menuWindow.hide();
                }
            }
            if (settings != null) {
                if (settings.checkCollision((int) x, (int) y) == true) {
                    settings.blink();
                    selectedScreen = variables.getSETTINGS_SCREEN();
                    cloudManager.start();
                }
            }
            if (authors != null) {
                if (authors.checkCollision((int) x, (int) y) == true) {
                    authors.blink();
                    selectedScreen = variables.getAUTHORS_SCREEN();
                    cloudManager.start();
                }
            }
            if (menu != null) {
                if (menu.checkCollision((int) x, (int) y) == true) {
                    menu.blink();
                    selectedScreen = variables.getMENU_SCREEN();
                    cloudManager.start();
                }
            }

            if (yellowSectionUpRight != null) {

                if (yellowSectionMiddle.checkCollision((int) x, (int) y) == true) {
                    yellowSectionMiddle.blink();
                    assetsManager.click.play();
                }
                if (yellowSectionLeft.checkCollision((int) x, (int) y) == true) {
                    assetsManager.click.play();
                    yellowSectionLeft.blink();
                }
                if (yellowSectionUpLeft.checkCollision((int) x, (int) y) == true) {
                    assetsManager.click.play();
                    yellowSectionUpLeft.blink();
                }
                if (yellowSectionUpRight.checkCollision((int) x, (int) y) == true) {
                    assetsManager.click.play();
                    yellowSectionUpRight.blink();
                }

            }

            if (up != null) {
                if (up.checkCollision((int) x, (int) y) == true) {
                    upAction();
                }
            }
            if (down != null) {
                if (down.checkCollision((int) x, (int) y) == true) {
                    downAction();
                }
            }

            if (runButton != null)
                if (runButton.checkCollision((int) x, (int) y) == true) {
                    runButtonAction();
                }

            if (jump != null)
                if (jump.checkCollision((int) x, (int) y) == true) {
                    jumpAction();
                }

            if (runButtonSecond != null)
                if (runButtonSecond.checkCollision((int) x, (int) y) == true) {
                    runButtonSecondAction();
                }

            if (pause != null)
                if (pause.checkCollision((int) x, (int) y) == true) {
                    pauseAction();

                }
            if (meetTheTrucks != null) {
                if (fireStation.checkCollision((int) x, (int) y) == true) {
                    fireStation.blink();
                }
                if (meetTheTrucks.checkCollision((int) x, (int) y) == true) {
                    meetTheTrucks.blink();
                    selectedScreen = variables.getMEET_THE_TRUCKS();
                    cloudManager.start();
                }

                for (int a = 0; a < 7; a++) {
                    if (levelButtons[a].checkCollision((int) x, (int) y) == true) {
                        levelButtons[a].blink();

                        if (a == 0 && screensPlayed[a] == true) {
                            selectedScreen = variables.getFITNESS_SCREEN_ONE();
                            cloudManager.start();
                        }
                        if (a == 1 && screensPlayed[a] == true) {
                            selectedScreen = variables.getTRAINING_SCREEN_ONE();
                            cloudManager.start();
                        }
                        if (a == 2 && screensPlayed[a] == true) {
                            selectedScreen = variables.getTRAINING_SCREEN_TWO();
                            cloudManager.start();
                        }
                        if (a == 3 && screensPlayed[a] == true) {
                            selectedScreen = variables.getCAT_RESCUE_SCREEN();
                            cloudManager.start();
                        }
                        if (a == 4 && screensPlayed[a] == true) {
                            selectedScreen = variables.getRESCUE_METRO_SCREEN();
                            cloudManager.start();
                        }
                        if (a == 5 && screensPlayed[a] == true) {
                            selectedScreen = variables.getELEVATOR_SCREEN();
                            cloudManager.start();
                        }
                        if (a == 6 && screensPlayed[a] == true) {
                            selectedScreen = variables
                                    .getBIG_ROAD_RESCUE_SCREEN();
                            cloudManager.start();
                        }
                    }
                }

            }
        }
    }

    public void setMenuWindow(MenuWindow menuWindow) {
        this.menuWindow = menuWindow;
        this.retryButton = menuWindow.getRetry();
        this.playButton = menuWindow.getPlay();
        this.menu = menuWindow.getMenu();
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

    public void setTextInputListener(MyTextInputListener textInputListener) {
        this.textInputListener = textInputListener;
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

    public void setSettings(Button settings) {
        this.settings = settings;
    }

    public void setGeneralPurposeButton(Button generalPurposeButton) {
        this.generalPurposeButton = generalPurposeButton;
    }

    public void setAuthors(Button authors) {
        this.authors = authors;
    }

    public void setRunButtonSecond(Button runButtonSecond) {
        this.runButtonSecond = runButtonSecond;
    }

    public void setControlledHuman(Human controlledHuman) {
        this.controlledHuman = controlledHuman;
    }

    public void setMenu(Button menu) {
        this.menu = menu;
    }

    public void setJump(Button jump) {
        this.jump = jump;
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

    public void setGenderButton(Button gender) {
        this.gender = gender;
    }

    public void setHitboxes(Button[] hitboxes) {
        this.hitboxes = hitboxes;
    }

    public void setSettingsButtons(Button fps, Button textureFiltering,
                                   Button voice, Button vibrations, Button screenAwake,
                                   Button noPrompts, Button resetGame, DataOrganizer dataOrganizer) {
        settingsButtons = new Button[7];
        settingsButtons[0] = fps;
        settingsButtons[1] = textureFiltering;
        settingsButtons[2] = voice;
        settingsButtons[3] = vibrations;
        settingsButtons[4] = screenAwake;
        settingsButtons[5] = noPrompts;
        settingsButtons[6] = resetGame;

        this.dataOrganizer = dataOrganizer;
    }

    public void setDataOrganizer(DataOrganizer dataOrganizer) {
        this.dataOrganizer = dataOrganizer;
    }

    public void setYellowSectionButtons(Button yellowSectionMiddle,
                                        Button yellowSectionLeft, Button yellowSectionUpLeft,
                                        Button yellowSectionUpRight) {
        this.yellowSectionMiddle = yellowSectionMiddle;
        this.yellowSectionLeft = yellowSectionLeft;
        this.yellowSectionUpLeft = yellowSectionUpLeft;
        this.yellowSectionUpRight = yellowSectionUpRight;
    }

    public void setScreensPlayed(boolean screensPlayed[]) {
        this.screensPlayed = screensPlayed;
    }

    public void setUserScreenButton(Button userScreenButton) {
        this.userScreenButton = userScreenButton;
    }

    public void setUnlockGameButton(Button unlockGameButton) {
        this.unlockGameButton = unlockGameButton;
    }

    void manageNonGuiCollisions(float x, float y) {

        if (eclipseFire != null)
            if (eclipseFire.checkCollision((int) x, (int) y) == true) {
                eclipseFireAction();
            }

        if (hitboxes != null) {
            for (int a = 0; a < hitboxes.length; a++) {
                if (hitboxes[a].checkCollision((int) x, (int) y) == true) {
                    hitboxes[a].blink();
                }
            }
        }
    }

    public void checkKeyboardInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
            if (dialogueWindow != null && dialogueWindow.isVisibile() == true)
                dialogueWindow.hide();
        }

        if (Gdx.input.isKeyJustPressed(Keys.SPACE))
            eclipseFireAction();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
            pauseAction();
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            upAction();
            jumpAction();
        }
        if (Gdx.input.isKeyJustPressed(Keys.DOWN))
            downAction();
        if (runButton != null && runButtonSecond == null)
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT)
                    || Gdx.input.isKeyJustPressed(Keys.LEFT))
                runButtonAction();
        if (runButton == null && runButtonSecond != null)
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
                runButtonSecondAction();
        if (runButton != null && runButtonSecond != null) {
            if (Gdx.input.isKeyJustPressed(Keys.LEFT))
                runButtonAction();
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
                runButtonSecondAction();
        }

    }

    public void setUserInputButtons(Button[] userInputButtons) {
        this.userInputButtons = userInputButtons;
    }

    public int getUserInputID() {
        return selectedUserInputID;
    }


    public void setWebButton(Button webButton) {
        this.webButton = webButton;
    }

    void eclipseFireAction() {
        if (eclipseFire != null
                && eclipseFire.isBlockedFromInteraction() == false)
            eclipseFire.blink();
    }

    void pauseAction() {
        if (menuWindow != null && menuWindow.isVisibile() == true
                && dialogueWindow != null
                && dialogueWindow.isVisibile() == false) {
            pause.blink();
            menuWindow.hide();
        }
        if (menuWindow != null && menuWindow.isVisibile() == false
                && dialogueWindow != null
                && dialogueWindow.isVisibile() == false) {
            pause.blink();
            menuWindow.popUp();
        }
    }

    void webButtonAction() {
        if (webButton != null && webButton.isBlockedFromInteraction() == false) {
            webButton.blink();
            Gdx.net.openURI(Variables.MR_TUSHAR_WEBSITE);
        }
    }

    void upAction() {
        if (up != null && up.isBlockedFromInteraction() == false) {
            up.blink();
            if (controlledTruck != null)
                controlledTruck.upLane();
        }
    }

    void downAction() {
        if (down != null && dialogueWindow != null
                && dialogueWindow.isVisibile() == false
                && down.isBlockedFromInteraction() == false) {
            down.blink();
            if (controlledTruck != null)
                controlledTruck.downLane();
        }
    }

    void runButtonAction() {
        if (runButton != null && dialogueWindow != null
                && dialogueWindow.isVisibile() == false
                && runButton.isBlockedFromInteraction() == false) {
            runButton.blink();

            if (controlledHuman != null)
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
    }

    void jumpAction() {
        if (jump != null && dialogueWindow != null
                && dialogueWindow.isVisibile() == false
                && jump.isBlockedFromInteraction() == false) {
            jump.blink();
            if (controlledHuman.getAccelerationJump() == -10
                    && controlledHuman.getY() == 35)
                controlledHuman.setAccelerationJump(13);
        }
    }

    void runButtonSecondAction() {
        if (runButtonSecond != null && dialogueWindow != null
                && dialogueWindow.isVisibile() == false
                && runButtonSecond.isBlockedFromInteraction() == false) {
            runButtonSecond.blink();
            if (controlledHuman != null)
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
    }

    public void setAssetsManager(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
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