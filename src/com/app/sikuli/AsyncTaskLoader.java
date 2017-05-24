package com.app.sikuli;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class AsyncTaskLoader extends SwingWorker<Void, Integer> {

	Runnable task, finalTask;

	public AsyncTaskLoader(Runnable runnableTask) {
		// TODO Auto-generated constructor stub
		runnableTask = task;

	}

	public void setTask(Runnable newTask) {
		this.task = newTask;
	}

	public void setFinalTask(Runnable newFinalTask) {
		this.finalTask = newFinalTask;
	}

	@Override
	protected Void doInBackground() throws Exception {

		task.run();

		return null;
		// TODO Auto-generated method stub

	}

	@Override
	protected void done() {
		finalTask.run();
	}

}
