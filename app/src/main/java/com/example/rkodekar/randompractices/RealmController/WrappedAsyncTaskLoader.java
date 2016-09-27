package com.example.rkodekar.randompractices.RealmController;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by rkodekar on 9/28/16.
 */
public abstract class WrappedAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mData;

    /**
     * Constructor of <code>WrappedAsyncTaskLoader</code>
     *
     * @param context The {@link Context} to use.
     */
    WrappedAsyncTaskLoader(Context context) {
        super(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deliverResult(D data) {
        if (!isReset()) {
            this.mData = data;
            super.deliverResult(data);
        } else {
            // An asynchronous query came in while the loader is stopped
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStartLoading() {
        if (this.mData != null) {
            deliverResult(this.mData);
        } else if (takeContentChanged() || this.mData == null) {
            forceLoad();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible
        cancelLoad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader is stopped
        onStopLoading();
        this.mData = null;
    }
}
