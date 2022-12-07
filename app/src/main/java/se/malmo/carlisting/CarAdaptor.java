package se.malmo.carlisting;
/*

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdaptor extends RecyclerView.Adapter<CarAdaptor.ViewHolder>  {
    Context context;
    ArrayList<Car> cars;

    public CarAdaptor(Context context, ArrayList<Car> cars){
        this.cars = cars;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.car_item_view, parent, false);

        ViewHolder holder = new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = cars.get(position);

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

            txtModel = itemView.findViewById(R.id.txtModel);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
 */
