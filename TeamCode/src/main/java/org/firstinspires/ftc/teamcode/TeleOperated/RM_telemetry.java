package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Arm_telemetry")
public class RM_telemetry extends OpMode {

    //motors
    DcMotor linearSlideLeft = null;
    DcMotor linearSlideRight = null;

    boolean armHold = false;



    public boolean turboMode = false;

    @Override
    public void init() {

        linearSlideLeft = hardwareMap.get(DcMotor.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");


        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        linearSlideLeft.setPower(0);
        linearSlideRight.setPower(0);


        linearSlideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {







        if (gamepad2.left_stick_y >= 0.3 || gamepad2.left_stick_y <= -0.3) {
            linearSlideLeft.setPower(gamepad2.left_stick_y/3);
            linearSlideRight.setPower(gamepad2.left_stick_y/3);
        }
        else {
            if (armHold == false) {
                linearSlideLeft.setPower(0);
                linearSlideRight.setPower(0);
            }
        }

    }
}
