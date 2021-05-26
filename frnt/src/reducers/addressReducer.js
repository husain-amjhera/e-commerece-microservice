import { ADD_ADDRESS } from "../actions/type";

const initialState = {
  list: []
};

export default function(state = initialState, action) {
  switch (action.type) {
    case ADD_ADDRESS:
      return {
        ...state,
        list: action.payload
      };

    default:
      return state;
  }
}
