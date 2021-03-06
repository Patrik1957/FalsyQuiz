package hu.falsyquiz.falsyquiz.Game;

import android.os.CountDownTimer;

/**
 * This class is responsible for managing the timer.
 */
public class Timer {

    public interface TimerListener {
        void tick(long timeLeft);
        void end();
    }

    public static final long DEFAULT_SHORT_TIME = 10000;
    public static final long DEFAULT_NORMAL_TIME = 25000;
    public static final long DEFAULT_TICK_TIME = 1000;
    public static final int QUESTION_TIME_SEC_LOWER_BOUND = 18;
    public static final int QUESTION_TIME_SEC_CORRIG = 12;

    private CountDownTimer countDownTimer;

    private TimerListener listener;

    public Timer(long time, final TimerListener listener) {
        this.listener = listener;

        initCountDownTimer(time, DEFAULT_TICK_TIME);
    }

    public Timer(long time, long tickTime, TimerListener listener) {
        this.listener = listener;
        initCountDownTimer(time, tickTime);
    }

    /**
     * This method initializes the timer.
     */
    private void initCountDownTimer(long time, long tickTime) {
        countDownTimer = new CountDownTimer(time, tickTime) {

            @Override
            public void onTick(long l) {
                listener.tick(l);
            }

            @Override
            public void onFinish() {
                listener.end();
            }
        };
    }

    /**
     * This method starts the timer.
     */
    public void start() {
        countDownTimer.start();
    }

    /**
     * This method stops the timer.
     */
    public void stop() {
        countDownTimer.cancel();
    }
}
