package org.rhok.pta.sifiso.donatemystuff.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * This is a model representation of the Donation-Offer
 * 
 * @author Ishmael Makitla
 * 
 */
public class DonationOffer {
	String id;
	String donorid;
	String donationrequestid;
	Date offerdate;
	DonatedItem item;
	int quantity = 0;
	boolean deliver;

	public DonationOffer() {
		//
		this.id = UUID.randomUUID().toString();
		this.offerdate = new Date();
	}

	public String getDonorid() {
		return donorid;
	}

	public void setDonorid(String donorid) {
		this.donorid = donorid;
	}

	public String getDonationrequestid() {
		return donationrequestid;
	}

	public void setDonationrequestid(String donationrequestid) {
		this.donationrequestid = donationrequestid;
	}

	public Date getOfferdate() {
		return offerdate;
	}

	public void setOfferdate(Date offerdate) {
		this.offerdate = offerdate;
	}

	public boolean isDeliver() {
		return deliver;
	}

	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
	}

	public String getDonorId() {
		return donorid;
	}

	public void setDonorId(String donorId) {
		this.donorid = donorId;
	}

	public String getDonationRequestId() {
		return donationrequestid;
	}

	public void setDonationRequestId(String donationRequestId) {
		this.donationrequestid = donationRequestId;
	}

	public DonatedItem getItem() {
		return item;
	}

	public void setItem(DonatedItem item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String _id) {
		this.id = _id;
	}

	public Date getOfferDate() {
		return offerdate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerdate = offerDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * This method returns a JSON version of the instantiated DonationOffer
	 * object
	 * 
	 * @return
	 */
	public JsonObject asJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("offer_date", offerdate.toString());
		json.addProperty("donor_id", donorid);
		json.addProperty("donation_request_id", donationrequestid);
		// donated item
		JsonObject donatedItemJson = null;
		if (item != null) {
			donatedItemJson = item.asJSON();
		}

		json.add("donated_item", donatedItemJson);
		json.addProperty("quantity", quantity);

		System.out.println(json.toString());

		return json;
	}

	/**
	 * 
	 * @param offer
	 * @return
	 * @throws JSONException
	 */
	public static DonationOffer fromJSONOffer(JSONObject offer)
			throws JSONException {
		DonationOffer offerObj = new DonationOffer();

		Date offerDate = null;
		String id = null;
		if (offer.get("id") != null) {
			id = offer.get("id").toString();
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

		String donorId = offer.get("donorid").toString();
		String donationRequestId = offer.get("donationrequestid").toString();

		JsonObject itemObj = (JsonObject) offer.get("item");

		DonatedItem item = DonatedItem.asDonatedItem(itemObj);

		if (offer.get("offerdate") != null) {
			String dateStr = offer.get("offerdate").toString();
			try {
				offerDate = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		offerObj.setDonationRequestId(donationRequestId);
		offerObj.setDonorId(donorId);
		offerObj.setItem(item);
		if (id != null) {
			offerObj.setId(id);
		}

		if (offerDate != null) {
			offerObj.setOfferDate(offerDate);
		}

		String offeredItemsCount = offer.get("quantity").toString();
		offerObj.setQuantity(Integer.parseInt(offeredItemsCount));

		return offerObj;
	}

	public static DonationOffer asDonationOffer(JsonObject json) {
		DonationOffer offer = new DonationOffer();

		Date offerDate = null;
		String id = null;
		if (json.get("id") != null) {
			id = json.get("id").toString();
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

		String donorId = json.get("donorid").toString();
		String donationRequestId = json.get("donationrequestid").toString();

		JsonObject itemObj = (JsonObject) json.get("item");

		DonatedItem item = DonatedItem.asDonatedItem(itemObj);

		if (json.get("offerdate") != null) {
			String dateStr = json.get("offerdate").toString();
			try {
				offerDate = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		offer.setDonationRequestId(donationRequestId);
		offer.setDonorId(donorId);
		offer.setItem(item);
		if (id != null) {
			offer.setId(id);
		}

		if (offerDate != null) {
			offer.setOfferDate(offerDate);
		}

		String offeredItemsCount = json.get("quantity").toString();
		offer.setQuantity(Integer.parseInt(offeredItemsCount));

		return offer;
	}

}