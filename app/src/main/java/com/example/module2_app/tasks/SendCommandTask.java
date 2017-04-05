package com.example.module2_app.tasks;

import android.os.AsyncTask;

import com.example.module2_app.MainActivity;
import com.example.module2_app.MessageConstants;
import com.example.module2_app.State;

public class SendCommandTask extends AsyncTask<SendCommandTask.CommandType, Void, Void> {

    public enum CommandType {
        UP, DOWN, LEFT, RIGHT, REST;
    }

    @Override
    protected Void doInBackground(CommandType... params) {
        while(!isCancelled()) {
            if (!MainActivity.mCanSendCommands.get())
                continue;

            // Only supports one action at the moment
            MainActivity.mCanSendCommands.set(false);
            switch (params[0]){
                case UP:
                    State.mmCommunicationThread.commandMoveTime(MessageConstants.MOVE_UP, 0);
                    break;
                case DOWN:
                    State.mmCommunicationThread.commandMoveTime(MessageConstants.MOVE_DOWN, 0);
                    break;
                case LEFT:
                    State.mmCommunicationThread.commandMoveTime(MessageConstants.MOVE_LEFT, 100000);
                    break;
                case RIGHT:
                    State.mmCommunicationThread.commandMoveTime(MessageConstants.MOVE_RIGHT, 100000);
                    break;
                default:
                    MainActivity.mCanSendCommands.set(true);
                    break;
            }

        }
        return null;
    }

    @Override
    protected void onCancelled() {

    }

    @Override
    protected void onPostExecute(Void result) {

    }
}
