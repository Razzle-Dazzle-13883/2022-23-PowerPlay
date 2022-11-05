package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Test_Teleop")
public class test_teleop extends OpMode {

    //motors
    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    Servo leftClaw = null;
    Servo rightClaw = null;




    public boolean turboMode = false;

    @Override
    public void init() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop() {

        //strafing stuff + Turbomode stuff
        double x = gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double r = -gamepad1.right_stick_x;
        if (turboMode) {
            leftFront.setPower((x + y + r) / 3.7);
            leftBack.setPower((x - y + r) / 3.7);
            rightFront.setPower((x - y - r) / 3.7);
            rightBack.setPower((x + y - r) / 3.7);
        } else {
            leftFront.setPower((x + y + r) / 5);
            leftBack.setPower((x - y + r) / 5);
            rightFront.setPower((x - y - r) / 5);
            rightBack.setPower((x + y - r) / 5);
        }

        //Fastmode and slowmode
        if (gamepad1.y == true) {
            turboMode = true;
        } else if (gamepad1.a == true) {
            turboMode = false;
        }
        double servoPosLeft = 0;
        double servoPosRight = 0;

        if (gamepad2.left_bumper == true) {
            servoPosLeft = servoPosLeft + 5;
            leftClaw.setPosition(servoPosLeft);

            servoPosRight = servoPosRight - 5;
            rightClaw.setPosition(servoPosRight);
        }


        if (gamepad2.right_bumper == true) {
            servoPosLeft = servoPosLeft - 5;
            leftClaw.setPosition(servoPosLeft);

            servoPosRight = servoPosRight + 5;
            rightClaw.setPosition(servoPosRight);
        }





    }
}