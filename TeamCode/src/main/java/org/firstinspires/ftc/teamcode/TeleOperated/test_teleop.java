package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Teleop")
public class test_teleop extends OpMode {

    //motors
    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlide = null;



    public boolean turboMode = false;

    @Override
    public void init() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
//        linearSlide = hardwareMap.get(DcMotor.class, "linearSlideLeft");


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
 //       linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
  //      linearSlide.setPower(0);


        leftClaw.setPosition(1);
        rightClaw.setPosition(0);





        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {


        //strafing stuff + Turbomode stuff
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double r = -gamepad1.right_stick_x;
        if (turboMode) {
            leftFront.setPower((forward + strafe + r));
            leftBack.setPower((forward - strafe + r));
            rightFront.setPower((forward - strafe - r));
            rightBack.setPower((forward + strafe - r));
        } else {
            leftFront.setPower((x + y + r) / 2);
            leftBack.setPower((x - y + r) / 2           );
            rightFront.setPower((x - y - r) / 2);
            rightBack.setPower((x + y - r) / 2);
        }

        if (gamepad1.y) {
            turboMode = true;
        }
        else if (gamepad1.a) {
            turboMode = false;
        }

        if (gamepad1.right_bumper == true) {
            leftClaw.setPosition(1);
            rightClaw.setPosition(0);


        }


        if (gamepad1.left_bumper == true) {
            leftClaw.setPosition(0.5);
            rightClaw.setPosition(0.5);

        }


//        if ((gamepad2.left_stick_y >= 0.3 || gamepad2.left_stick_y <= -0.3)) {
   //         linearSlide.setPower(gamepad2.left_stick_y / 2);


      //  }


    }
}