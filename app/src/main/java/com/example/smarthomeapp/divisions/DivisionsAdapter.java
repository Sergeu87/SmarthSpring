package com.example.smarthomeapp.divisions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.util.IconUtils;
import com.example.utils.domain.Division;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Razovyi on 20-Oct-19.
 */
public class DivisionsAdapter extends BaseAdapter{

    private List<Division> mDivisionsList;
    private Context mContext;

    /**
     * Instantiates a new Divisions adapter.
     *
     * @param context       the context
     * @param mPresenter    the m presenter
     * @param divisionsList the divisions list
     */
    public DivisionsAdapter(Context context, BasePresenter mPresenter, List<Division> divisionsList){
        this.mDivisionsList = divisionsList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDivisionsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDivisionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DivisionsViewHolder holder;

        if (convertView != null) {
            holder = (DivisionsViewHolder) convertView.getTag();

        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            // pobierz układ z division_item.xml
            convertView = inflater.inflate(R.layout.division_item, parent, false);
            holder = new DivisionsViewHolder(convertView);
            convertView.setTag(holder);
        }

        Division division = mDivisionsList.get(position);

        holder.divisionText.setText(division.getName());
        holder.divisionImage.setImageResource(IconUtils.getDivisionsIconsMap().get(division.getRefDivisionType()));

        return convertView;
    }

    /**
     * Replace data.
     *
     * @param divisions the divisions
     */
    public void replaceData(List<Division> divisions) {
        setList(divisions);
        notifyDataSetChanged();
    }

    private void setList(List<Division> divisions) {
        mDivisionsList = divisions;
    }

    /**
     * The type Divisions view holder.
     */
    public class DivisionsViewHolder {
        /**
         * The Division image.
         */
        @BindView(R.id.division_image)
        ImageView divisionImage;
        /**
         * The Division text.
         */
        @BindView(R.id.division_text)
        TextView divisionText;

        /**
         * Instantiates a new Divisions view holder.
         *
         * @param view the view
         */
        public DivisionsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
