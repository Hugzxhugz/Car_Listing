package se.malmo.carlisting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyCarsAdaptor extends RecyclerView.Adapter<MyCarsAdaptor.ViewHolder> {
    Context context;
    ArrayList<Car> cars;

    public MyCarsAdaptor(Context context, ArrayList<Car> cars){
        this.cars = cars;
        this.context = context;
    }


    @NonNull
    @Override
    public MyCarsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView2 = inflater.inflate(R.layout.mycar_item_view, parent, false);

        MyCarsAdaptor.ViewHolder holder2 = new MyCarsAdaptor.ViewHolder(itemView2);

        itemView2.setOnClickListener(View -> {
            Intent intent = new Intent(context, DisplayActivity.class);
            int car_id = Integer.parseInt(holder2.txtModel.getTag().toString());
            intent.putExtra("id", car_id);
            context.startActivity(intent);
        });
        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCarsAdaptor.ViewHolder holder, int position) {
        Car car = cars.get(position);

        holder.txtModel.setTag(car.getId());
        holder.txtModel.setText(car.getModel());
        holder.txtBrand.setText(car.getBrand());
        holder.txtPrice.setText(car.getPrice()+" kr");
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtModel, txtBrand, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtModel = itemView.findViewById(R.id.mycar_model_label);
            txtBrand = itemView.findViewById(R.id.mycar_brand_label);
            txtPrice = itemView.findViewById(R.id.mycar_price_label);
        }
    }
}
