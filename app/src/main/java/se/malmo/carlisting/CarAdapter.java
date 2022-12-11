package se.malmo.carlisting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>  {
    Context context;
    ArrayList<Car> cars;

    public CarAdapter(Context context, ArrayList<Car> cars){
        this.cars = cars;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_row_view, parent, false);

        ViewHolder holder = new ViewHolder(itemView);

        itemView.setOnClickListener(View -> {
                Intent intent = new Intent(context, DisplayActivity.class);
                int car_id = Integer.parseInt(holder.txtModel.getTag().toString());
                intent.putExtra("id", car_id);
                context.startActivity(intent);
            });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        public final Button btnBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnBuy = itemView.findViewById(R.id.btnBuyCar);
            txtModel = itemView.findViewById(R.id.car_model_label);
            txtBrand = itemView.findViewById(R.id.car_brand_label);
            txtPrice = itemView.findViewById(R.id.car_price_label);

            btnBuy.setOnClickListener(view -> {
                Repository carRepo = SqliteCarRepository.getInstance(context);
                boolean buy = carRepo.attemptBuyCar(Integer.parseInt(txtModel.getTag().toString()));
                if(buy){
                    Intent intent = new Intent(context, MyCarsActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
