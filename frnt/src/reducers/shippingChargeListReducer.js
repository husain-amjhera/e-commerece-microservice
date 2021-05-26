import { GET_SHIPPING_CHARGE_LIST } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_SHIPPING_CHARGE_LIST:
      return action.payload;

    default:
      return state;
  }
}
