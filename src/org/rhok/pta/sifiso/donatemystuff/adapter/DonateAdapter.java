package org.rhok.pta.sifiso.donatemystuff.adapter;

import org.rhok.pta.sifiso.donatemystuff.BlanketDonationActivity;
import org.rhok.pta.sifiso.donatemystuff.BookDonateActivity;
import org.rhok.pta.sifiso.donatemystuff.ClothDonationActivity;
import org.rhok.pta.sifiso.donatemystuff.R;
import org.rhok.pta.sifiso.donatemystuff.ShoesDonationActivity;
import org.rhok.pta.sifiso.donatemystuff.model.UserSession;
import org.rhok.pta.sifiso.donatemystuff.util.DonateMyStuffGlobals;
import org.rhok.pta.sifiso.donatemystuff.util.FontChanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * 
 * 
 * @author sifiso mtshweni
 * 
 */
public class DonateAdapter extends BaseAdapter {

	public enum DonationType {
		SHOES, CLOTHES, BOOKS, BLANKETS
	};

	private Context context;
	private String[] items;

	private static final int[] mThumbIds = { R.drawable.book, R.drawable.cloth,
			R.drawable.shoe, R.drawable.blanket };

	public DonateAdapter(Context context) {
		this.context = context;
		items = context.getResources().getStringArray(R.array.donatio_items);

	}

	/**
	 * Session Handling
	 */
	private UserSession session;

	public void setSession(UserSession _session) {
		this.session = _session;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup root) {

		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			LayoutInflater infalter = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = infalter.inflate(R.layout.cards, root, false);
			holder.img = (ImageView) convertView
					.findViewById(R.id.recarga_thumb);
			holder.click = (View) convertView.findViewById(R.id.clickView2);
			holder.textItem = (TextView) convertView.findViewById(R.id.item);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Picasso.with(context).load(mThumbIds[position]).resize(300, 300).into(holder.img);
		holder.click.setOnClickListener(new CustomClickListener(
				items[position], position));
		holder.textItem.setText(items[position]);
		holder.textItem.setTypeface(FontChanger.setRobotoLight(context));
		DonationType type = deriveDonationType(position);
		holder.type = type;
		convertView.setTag(holder);

		Log.d(DonateAdapter.class.getSimpleName(), "Item At Position "
				+ position + " is " + type);

		return convertView;
	}

	private DonationType deriveDonationType(int typeCode) {
		DonationType donationType = null;
		switch (typeCode) {
		case 0:
			donationType = DonationType.BOOKS;
			break;
		case 1:
			donationType = DonationType.CLOTHES;
			break;
		case 2:
			donationType = DonationType.SHOES;
			break;
		case 3:
			donationType = DonationType.BLANKETS;
			break;

		}

		return donationType;
	}

	public class ViewHolder {
		ImageView img;
		View click;
		TextView textItem;
		DonationType type;
	}

	public class CustomClickListener implements View.OnClickListener {
		String args;
		int position;

		public CustomClickListener(String args, int viewPosition) {
			this.args = args;
			this.position = viewPosition;
			Log.d(DonateAdapter.class.getSimpleName(),
					" CustomClickListener:: " + args + " position::" + position);
		}

		@Override
		public void onClick(View view) {
			Intent intent = null;
			Bundle bundle = new Bundle();
			bundle.putInt(DonateMyStuffGlobals.KEY_MODE,
					DonateMyStuffGlobals.MODE_OFFERS_LIST);
			// set session
			bundle.putSerializable(DonateMyStuffGlobals.KEY_SESSION, session);
			DonationType type = deriveDonationType(position);

			Log.d(DonateAdapter.class.getSimpleName(),
					" Clicked-Donation-Type:: " + type);
			switch (type) {
			case BOOKS:
				intent = new Intent(context, BookDonateActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				break;
			case BLANKETS:
				intent = new Intent(context, BlanketDonationActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				break;
			case CLOTHES:
				intent = new Intent(context, ClothDonationActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				break;
			case SHOES:
				intent = new Intent(context, ShoesDonationActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				break;

			}

			if (intent != null) {
				intent.putExtras(bundle);
				context.startActivity(intent);
			}

		}

	}
}
