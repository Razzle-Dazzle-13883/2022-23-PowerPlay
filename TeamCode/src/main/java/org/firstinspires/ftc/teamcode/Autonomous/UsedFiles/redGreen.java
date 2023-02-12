package org.firstinspires.ftc.teamcode.Autonomous.UsedFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "rightGreen")

public class redGreen extends LinearOpMode {
    private DcMotor rightFront = null;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlideLeft = null;
    DcMotor linearSlideRight = null;

    int leftFrontPos = 0;
    int rightFrontPos = 0;
    int leftBackPos = 0;
    int rightBackPos = 0;
    int linearSlidePos = 0;




    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        linearSlideLeft = hardwareMap.dcMotor.get("linearSlideLeft");
        linearSlideRight = hardwareMap.dcMotor.get("linearSlideRight");


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftClaw.setPosition(1);
        rightClaw.setPosition(0);


        waitForStart();





//        up(700, 1);

        forward(-1000, 0.2);
        sleep(20);

        right(2000, 0.2);
        sleep(20);

        forward(300, 0.05);
        sleep(10);

//        up(300, 0.25);

        forward(100, 0.1);

        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.85);

        forward(-300, 0.05);

        right(200, 0.1);

        forward(2200, 0.2);

    }



    private void up (int up, double speed) {
        linearSlidePos += up;

        linearSlideLeft.setTargetPosition(linearSlidePos);
        linearSlideRight.setTargetPosition((linearSlidePos));

        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideLeft.setPower(speed);
        linearSlideRight.setPower(speed);

        while (opModeIsActive() && linearSlideLeft.isBusy() && linearSlideRight.isBusy()) {
            idle();
        }
    }
    private void forward(int distance, double speed  ) {
        leftFrontPos = leftFrontPos - distance;
        rightFrontPos = rightFrontPos - distance;
        leftBackPos = leftBackPos - distance;
        rightBackPos = rightBackPos - distance;


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


    private void right(int distance, double speed  ) {
        leftFrontPos = leftFrontPos - distance;
        rightFrontPos = rightFrontPos + distance;
        leftBackPos = leftBackPos + distance;
        rightBackPos = rightBackPos - distance;


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


    private void left(int distance, double speed  ) {
        leftFrontPos = leftFrontPos - distance;
        rightFrontPos = rightFrontPos + distance;
        leftBackPos = leftBackPos + distance;
        rightBackPos = rightBackPos - distance;

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


    private void custom(int leftFrontTarget, int rightFrontTarget, int leftBackTarget, int rightBackTarget, double speed  ) {
        leftFrontPos -= leftFrontTarget;
        rightFrontPos -= rightFrontTarget;
        leftBackPos -= leftBackTarget;
        rightBackPos -= rightBackTarget;

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
}
