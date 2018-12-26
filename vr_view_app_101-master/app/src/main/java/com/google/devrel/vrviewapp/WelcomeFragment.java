/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.devrel.vrviewapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

/**
 * Fragment for handling the Welcome tab.
 */
public class WelcomeFragment extends Fragment {

    private VrPanoramaView panowidgetView;
    ImageLoaderTask backgroundImageLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.welcome_fragment, container,false);
        panowidgetView = (VrPanoramaView) v.findViewById(R.id.pano_view);
        return v;
    }

    @Override
    public void onPause() {
        panowidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        panowidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        panowidgetView.shutdown();
        super.onDestroy();
    }

    private synchronized void loadPanoImage(){
        ImageLoaderTask task = backgroundImageLoader;
        if(task!= null && !task.isCancelled())
            task.cancel(true);

        //pass in the image to load from the asset
        VrPanoramaView.Options viewoptions = new VrPanoramaView.Options();
        viewoptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        //use the name of the image in assets directory
        String panoImageName = "sample_converted.jpg";

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panowidgetView, viewoptions, panoImageName);
        task.execute(getActivity().getAssets());
        backgroundImageLoader = task;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPanoImage();
    }
}
