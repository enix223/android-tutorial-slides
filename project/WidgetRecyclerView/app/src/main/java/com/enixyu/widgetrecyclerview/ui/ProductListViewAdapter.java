package com.enixyu.widgetrecyclerview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.enixyu.widgetrecyclerview.R;
import com.enixyu.widgetrecyclerview.model.Product;
import com.enixyu.widgetrecyclerview.model.ProductRepository;
import java.util.Locale;

public class ProductListViewAdapter extends
    RecyclerView.Adapter<ProductListViewAdapter.ViewHolder> {

  private final ProductRepository productRepository;

  public ProductListViewAdapter(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_product, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Product product = productRepository.getByIndex(position);
    if (product == null) {
      throw new RuntimeException("记录不存在");
    }
    holder.titleLabel.setText(product.getName());
    holder.priceLabel.setText(String.format(Locale.getDefault(), "%.2f", product.getPrice()));
  }

  @Override
  public int getItemCount() {
    return productRepository.count();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleLabel;

    private final TextView priceLabel;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      titleLabel = itemView.findViewById(R.id.tv_product_title);
      priceLabel = itemView.findViewById(R.id.tv_product_price);
    }
  }
}
