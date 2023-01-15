package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "blueAuto")
@Disabled
public class blueAuto extends LinearOpMode {


    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlide = null;

    int leftFrontPos;
    int rightFrontPos;
    int leftBackPos;
    int rightBackPos;


    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlides");


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.85);


        leftFrontPos = 0;
        rightFrontPos = 0;
        leftBackPos = 0;
        rightBackPos = 0;

        waitForStart();


        //1 inch is 38

        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
        sleep(1000);
        // closes claws to grab cone


        drive(-38 * 20, 38 * 20, 38 * 20, -38 * 20, .25);

        drive(38 * 22, 38 * 22, 38 * 22, 38 * 22, .25);

        sleep(1000);
        // moves right towards the small junction and goes forwards

        linearSlide.setPower(1);

        sleep(1000);
        // brings linear slide up

        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.85);

        drive(-38 * 10, -38 * 10, -38 * 10, -38 * 10, .25);

        linearSlide.setPower(-1);

        sleep(1000);
        // opens claws to release cone, moves away from junction, brings linear slides down

        drive(38*22, -38*22, -38*22, 38*22, 0.25);

        drive(38*20, 38*20, 38*20, 38*20, 0.25);

        sleep(1000);

        // moves left & moves forward to go to middle zone

        drive(-38*10, 38*10, -38*10, 38*10, 0.25);

        sleep(1000);

        // rotates to point towards substation
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
}