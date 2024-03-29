import { ADD_TO_CART } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case ADD_TO_CART:
      return action.payload;

    default:
      return state;
  }
}
