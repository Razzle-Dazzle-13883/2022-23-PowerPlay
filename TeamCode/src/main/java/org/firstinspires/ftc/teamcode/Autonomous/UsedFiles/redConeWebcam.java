package org.firstinspires.ftc.teamcode.Autonomous.UsedFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "WebcamDetection")
@Disabled
public class redConeWebcam extends LinearOpMode {

    private static final String TFOD_MODEL_ASSET = "/sdcard/FIRST/tflitemodels/remoteEvent.tflite";
    private static final String[] LABELS = {
            "CappingElement"
            //




            // CHANGE WHEN LABELS ARE MADE




            //
    };

    private static final String VUFORIA_KEY =
            "AQ5Ga0r/////AAABmY3dPUqJZE+9lWYfmEZA8GxAAge/a1bHQ8udF/kH7f+UjIKGtq1629AE2grZt0+aJjE2DUw3Vd7X6lP23pVERIZtyIHHEqjz5/6o2Fvnjh0Hi3Bc34cG8W8JBoxzq8PDJ6ucRNP83vFx4dyaBuu/S3Il+YyDbTPnMC0eGdjWMyolP9d82QW2b/rsrJln8qnQIoI0qBV2YbUrV/5xyNZ9f+eyOhk3X/hgNsRKFoexNZERZbYxEU3d37adTXMMY6m2msmOP+H9/PLpgMMe1hRfrQbL+iNmLPVZqPZaRevhDFa57biNixyRuH8wERJlHI49BvrNeGshYa7yaQnNU7eGjSiyZt6DvopUXWXiyIqlcuM6\n";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;


    int leftFrontPos;
    int rightFrontPos;
    int leftBackPos;
    int rightBackPos;


    @Override
    public void runOpMode() {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");


        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();


            tfod.setZoom(1, 16.0/7.0);
        }


        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);



        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontPos = 0;
        rightFrontPos = 0;
        leftBackPos = 0;
        rightBackPos = 0;


        telemetry.addData(">", "DONE INTIALIZING | CHECK CAMERA STREAM FOR POSITION");
        telemetry.update();


        waitForStart();

        if (opModeIsActive()) {
            int counter = 0;
            int a = 0;
            int b = 0;
            int c = 0;
            while (counter <= 3) {
                while (opModeIsActive()) {
                    if (tfod != null) {
                        // getUpdatedRecognitions() will return null if no new information is available since
                        // the last time that call was made.
                        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                        if (updatedRecognitions != null) {
                            telemetry.addData("# Object Detected", updatedRecognitions.size());
                            int i = 0;
                            for (Recognition recognition : updatedRecognitions) {
                                telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                                telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                        recognition.getLeft(), recognition.getTop());
                                telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                        recognition.getRight(), recognition.getBottom());



                                //POTENCIAL ISSUE WITH GET LEFT NOT GETTING THE LABEL
                                if (recognition.getRight() < 200) {
                                    telemetry.addData("LEVEL3", recognition.getRight());
                                    c += 1; //level1
                                } else if (recognition.getRight() > 300) {
                                    telemetry.addData("LEVEL2", recognition.getRight());
                                    b += 1;  //level3
                                } else {
                                    telemetry.addData("LEVEL1", recognition.getRight());
                                    a += 1;  //level2
                                }



                                i++;
                            }
                            telemetry.update();
                        }
                    }
                    if (a > b && a > c) {
                        left();
                    } else if (b > a && b > c){
                        middle();
                    } else if (c > a && c > b) {
                        right();
                    } else{
                        middle();
                    }
                    telemetry.update();
                    sleep(5000);
                }
                if (tfod != null) {
                    tfod.shutdown();
                }

            }

        }
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void drive(int leftFrontTarget, int rightFrontTarget, int leftBackTarget, int rightBackTarget, double speed  ) {
        leftFrontPos += leftFrontTarget;
        rightFrontPos += rightFrontTarget;
        leftBackPos += leftBackTarget;
        rightBackPos += rightBackTarget;

        leftFront.setTargetPosition(leftFrontPos);
        rightFront.setTargetPosition(rightFrontPos);
        leftBack.setTargetPosition(leftBackPos);
        rightBack.setTargetPosition(rightBackPos);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {
            idle();
        }
    }





    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromFile(TFOD_MODEL_ASSET, LABELS);

    }


    //Left
    public void left() {
        telemetry.addData("Left:", "LEVEL1");
        telemetry.update();

        stop();


    }


    //MID (LIKE MHA)
    public void middle() {
        telemetry.addData("Middle:", "LEVEL2");
        telemetry.update();

        stop();
    }



    //Right
    public void right() {
        telemetry.addData("right:", "LEVEL3");
        telemetry.update();

        stop();
    }
}
