import { GET_ORDERS } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ORDERS:
      return action.payload;

    default:
      return state;
  }
}
