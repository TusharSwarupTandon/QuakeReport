package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    private static final String Location_Separator = "of ";
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> quake)
    {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, quake);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the earthquake_list_item.xml layout with the ID magnitude
        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude);
        //Format the magnitude to show one decimal place
        String magnitudeFormatted = formatMagnitude(currentEarthquake.getMagnitude());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the magnitude from the current Earthquake object and
        // set this text on the name TextView
        magnitudeTextView.setText(magnitudeFormatted);

        String[] location = currentEarthquake.getLocation().split(Location_Separator);
        String near = "", primary = "";
        if(location.length == 1)
        {
            near = "Near Of";
            primary = location[0];
        }
        else
        {
            near = location[0] + Location_Separator;
            primary = location[1];
        }
        // Find the TextView in the earthquake_list_item.xml layout with the ID nearOf
        TextView nearOfTextView =listItemView.findViewById(R.id.nearOf);
        // Get the location from the current Earthquake object and
        // set this text on the name TextView
        nearOfTextView.setText(near);

        // Find the TextView in the earthquake_list_item.xml layout with the ID primary_location
        TextView primaryLocationTextView =listItemView.findViewById(R.id.primary_location);
        // Get the location from the current Earthquake object and
        // set this text on the name TextView
        primaryLocationTextView.setText(primary);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Find the TextView in the earthquake_list_item.xml layout with the ID date
        TextView dateTextView = listItemView.findViewById(R.id.date);
        //Format the date string (JAN 12, 1990)
        String dateToDisplay = formatDate(dateObject);
        // Get the date from the current Earthquake object and
        // set this text on the name TextView
        dateTextView.setText(dateToDisplay);

        // Find the TextView in the earthquake_list_item.xml layout with the ID time
        TextView timeTextView = listItemView.findViewById(R.id.time);
        //Format the time string (3:40 PM)
        String timeToDisplay = formatTime(dateObject);
        // Get the date from the current Earthquake object and
        // set this text on the name TextView
        timeTextView.setText(timeToDisplay);

//        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
//
//        //check if current word has image
//        if(currentWord.hasImage())
//        {
//            // Get the image resource ID from the current AndroidFlavor object and
//            // set the image to iconView
//            iconView.setImageResource(currentWord.getImageResourceId());
//            //set icon view as visible
//            iconView.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            iconView.setVisibility(View.GONE);
//        }
//
//        //Set the theme color for the list item
//        View textContainer = listItemView.findViewById(R.id.textHolder);
//        //Find the color that resource id maps to
//        int color = ContextCompat.getColor(getContext(), mColorResourceId);
//        //Set the background color of textHolder view
//        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject)
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     *
     * return the formatted magnitude with one decimal place
     */
    private String formatMagnitude(double magnitude)
    {
        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(magnitude);
    }


    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);
        switch(magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
