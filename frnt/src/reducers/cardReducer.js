import { ADD_CARD } from "../actions/type";

const initialState = {
  list: []
};

export default function(state = initialState, action) {
  switch (action.type) {
    case ADD_CARD:
      return {
        ...state,
        list: action.payload
      };

    default:
      return state;
  }
}
