import { GET_PROFILE } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PROFILE:
      return action.payload;

    default:
      return state;
  }
}
