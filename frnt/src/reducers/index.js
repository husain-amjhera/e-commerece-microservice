import { combineReducers } from "redux";
import productReducer from "./productReducer";
import userReducer from "./userReducer";
import errorReducer from "./errorReducer";
import cartReducer from "./cartReducer";
import cardReducer from "./cardReducer";
import addressReducer from "./addressReducer";
import OrderReducer from "./orderReducer";
import profileReducer from "./profileReducer";
import wishListReducer from "./wishListReducer";
import invoiceReducer from "./invoiceReducer";
import shippingChargeListReducer from "./shippingChargeListReducer";

const rootReducer = combineReducers({
  products: productReducer,
  currentUser: userReducer,
  errors: errorReducer,
  cart: cartReducer,
  card: cardReducer,
  address: addressReducer,
  orders: OrderReducer,
  profile: profileReducer,
  wishList: wishListReducer,
  invoice: invoiceReducer,
  shippingChargeList: shippingChargeListReducer
});

export default rootReducer;
