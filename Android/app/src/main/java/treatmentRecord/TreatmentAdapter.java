package treatmentRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.westchen.phr.R;

import java.util.List;

/**
 * Created by 吴俊达 on 2016/3/19.
 */
public class TreatmentAdapter extends BaseAdapter {

    private LayoutInflater inflater ;

    private List<Treatment> data;

    public TreatmentAdapter(Context context, List<Treatment> data){

        this.inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Treatment getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Treatment treatment = data.get(position);
        ViewHolder viewHolder;

        //当当前列表项不存在时，生成一个列表项
        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.treatment, null);

            viewHolder.hospital = (TextView)convertView.findViewById(R.id.hospital);
            viewHolder.kind = (TextView)convertView.findViewById(R.id.kind);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date);
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.section = (TextView)convertView.findViewById(R.id.section);
            viewHolder.price = (TextView)convertView.findViewById(R.id.price);
            viewHolder.doctorName = (TextView)convertView.findViewById(R.id.doctorName);
            viewHolder.doctorNumber = (TextView)convertView.findViewById(R.id.doctorID);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.hospital.setText(treatment.getHospital());
        viewHolder.kind.setText(treatment.getKind());
        viewHolder.date.setText(treatment.getDate());
        viewHolder.time.setText(treatment.getTime());
        viewHolder.section.setText(treatment.getSection());
        viewHolder.price.setText("￥" + treatment.getPrice());
        viewHolder.doctorNumber.setText(treatment.getDoctorID());
        viewHolder.doctorName.setText(treatment.getDoctorName());

        return convertView;
    }
}




class ViewHolder{
    TextView hospital;
    TextView kind;
    TextView time;
    TextView date;
    TextView section;
    TextView doctorName;
    TextView doctorNumber;
    TextView price;
}