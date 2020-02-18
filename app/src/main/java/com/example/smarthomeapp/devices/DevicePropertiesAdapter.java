package com.example.smarthomeapp.devices;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.httpentities.PropertyValueResponse;
import com.example.smarthomeapp.model.EnumValueType;
import com.example.smarthomeapp.model.Enumerated;
import com.example.smarthomeapp.model.HomeConfigEntity;
import com.example.smarthomeapp.model.Property;
import com.example.smarthomeapp.model.ScalarValueType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicePropertiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private HomeConfigEntity mConfigEntity;
    private String mDeviceTypeId;
    private List<PropertyValueResponse> mPropertyValueResponses;
    private Property mCurrentProperty;
    private PropertyValueResponse mCurrentPropertyValueResponse;
    private int mViewType;

    private int ENUM = 1;
    private int SCALAR = 2;

    /**
     * Instantiates a new Device properties adapter.
     *
     * @param context                the context
     * @param deviceTypeId           the device type id
     * @param propertyValueResponses the property value responses
     */
    public DevicePropertiesAdapter(
            Context context,
            String deviceTypeId,
            List<PropertyValueResponse> propertyValueResponses
    ) {
        mContext = context;
        mDeviceTypeId = deviceTypeId;
        mConfigEntity = SmartHomeApplication.getInstance().getHomeConfiguration();
        mPropertyValueResponses = propertyValueResponses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == ENUM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.property_enum, parent, false);
            viewHolder = new EnumPropertyViewHolder(view);

        } else if (viewType == SCALAR) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.property_scalar, parent, false);
            viewHolder = new ScalarPropertyViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (mViewType == ENUM) {
            EnumPropertyViewHolder enumHolder = (EnumPropertyViewHolder) holder;

            enumHolder.enumTitle.setText(mCurrentProperty.getName());
            final EnumValueType enumValueType = mConfigEntity.getEnumByID(mCurrentProperty.getRefValueType());
            enumHolder.enumSelectionList.setLayoutManager(new LinearLayoutManager(mContext));
            final String propertyValue = mCurrentPropertyValueResponse.getPropertyValue();
            enumHolder.enumSelectionList.setAdapter(new SelectableOptionsListViewAdapter(enumValueType.getEnumerated(), propertyValue));


        } else if (mViewType == SCALAR) {
            ScalarPropertyViewHolder scalarHolder = (ScalarPropertyViewHolder) holder;

            scalarHolder.scalarTitle.setText(mCurrentProperty.getName());

            ScalarValueType scalarValueType = mConfigEntity.getScalarByID(mCurrentProperty.getRefValueType());
            scalarHolder.scalarMin.setText(scalarValueType.getMinValue());
            scalarHolder.scalarMax.setText(scalarValueType.getMaxValue());

            scalarHolder.scalarSeekBar.setMax(Integer.valueOf(scalarValueType.getMaxValue()));
            scalarHolder.scalarSeekBar.setProgress(Integer.valueOf(mCurrentPropertyValueResponse.getPropertyValue()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        mCurrentPropertyValueResponse = mPropertyValueResponses.get(position);
        mCurrentProperty = mConfigEntity.getPropertyFromDeviceTypeByID(
                mDeviceTypeId,
                mCurrentPropertyValueResponse.getPropertyId()
        );

        int viewType;
        switch (mCurrentProperty.getValueType()) {
            case "ENUM":
                viewType = ENUM;
                break;
            case "SCALAR":
                viewType = SCALAR;
                break;
            default:
                viewType = 0;
        }
        mViewType = viewType;
        return viewType;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mPropertyValueResponses.size();
    }

    /**
     * The type Enum property view holder.
     */
    public class EnumPropertyViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Enum title.
         */
        @BindView(R.id.enum_title)
        TextView enumTitle;
        /**
         * The Enum selection list.
         */
        @BindView(R.id.enum_selection_view)
        RecyclerView enumSelectionList;

        /**
         * Instantiates a new Enum property view holder.
         *
         * @param view the view
         */
        public EnumPropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * The type Scalar property view holder.
     */
    public class ScalarPropertyViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Scalar title.
         */
        @BindView(R.id.scalar_title)
        TextView scalarTitle;
        /**
         * The Scalar max.
         */
        @BindView(R.id.scalar_max)
        TextView scalarMax;
        /**
         * The Scalar min.
         */
        @BindView(R.id.scalar_min)
        TextView scalarMin;
        /**
         * The Scalar seek bar.
         */
        @BindView(R.id.scalar_seek_bar)
        SeekBar scalarSeekBar;

        /**
         * Instantiates a new Scalar property view holder.
         *
         * @param view the view
         */
        public ScalarPropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * The type Selectable options list view adapter.
     */
    public class SelectableOptionsListViewAdapter extends RecyclerView.Adapter<EnumeratedViewHolder> {

        private List<Enumerated> _enumeratedOptionsList;
        private String selectedEnumValueAsString;
        private RadioGroup radioGroup;
        private boolean added;

        /**
         * Instantiates a new Selectable options list view adapter.
         *
         * @param enumeratedOptions the enumerated options
         */
        public SelectableOptionsListViewAdapter(List<Enumerated> enumeratedOptions, String selectedEnumValue) {
            this._enumeratedOptionsList = enumeratedOptions;
            this.selectedEnumValueAsString = selectedEnumValue;
            this.radioGroup = new RadioGroup(mContext);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedItemIndex) {
                }
            });
        }

        @Override
        public EnumeratedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.enumerated_item, parent, false);
            final EnumeratedViewHolder enumeratedViewHolder = new EnumeratedViewHolder(view);
            return enumeratedViewHolder;
        }

        @Override
        public void onBindViewHolder(EnumeratedViewHolder holder, int position) {
            final Enumerated enumerated = _enumeratedOptionsList.get(position);

//            final RadioButton enumeratedRadioButton = new RadioButton(mContext);
            final RadioButton enumeratedRadioButton = holder.enumeratedRadioButton;
            enumeratedRadioButton.setText(enumerated.getName());
            enumeratedRadioButton.setId(position);

            final ViewGroup parent = (ViewGroup) enumeratedRadioButton.getParent();
            if (parent != null) {
                parent.removeView(enumeratedRadioButton);
                if (!added) {
                    parent.addView(radioGroup);
                    added = true;
                }

            }
            radioGroup.addView(enumeratedRadioButton);

            if (enumerated.getName().equals(selectedEnumValueAsString)) {
                radioGroup.clearCheck();
                enumeratedRadioButton.setChecked(true);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return _enumeratedOptionsList.size();
        }
    }

    /**
     * The type Enumerated view holder.
     */
    public class EnumeratedViewHolder extends RecyclerView.ViewHolder{
        /**
         * The Enumerated text.
         */
        @BindView(R.id.enumerated_rounded_checkbox)
        RadioButton enumeratedRadioButton;

        /**
         * Instantiates a new Enumerated view holder.
         *
         * @param view the view
         */
        public EnumeratedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
