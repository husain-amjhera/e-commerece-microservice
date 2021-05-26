import React, { useState } from "react";
import { useSelector } from "react-redux";
import classnames from "classnames";
import "./Register.css";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { registerUser } from "../actions/userActions";
function Register() {
  const history = useHistory();
  const dispatch = useDispatch();
  const errors = useSelector(state => state.errors);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [registerRequest, setRegisterRequest] = useState({
    fullName: "",
    email: "",
    password: ""
  });
  const [message, setMessage] = useState({
    matches: false,
    css: "",
    isSet: false
  });

  const onChange = e => {
    const { name, value } = e.target;
    setRegisterRequest(content => ({ ...content, [name]: value }));
  };

  const validatePassword = e => {
    if (e.target.value === registerRequest.password) {
      setMessage({ matches: true, css: "text-success", isSet: true });
    } else if (e.target.value !== registerRequest.password) {
      setMessage({
        matches: false,
        css: "text-danger",
        isSet: true
      });
    }
  };

  const handleSubmit = event => {
    event.preventDefault();
    dispatch(registerUser(registerRequest, history));
  };
  return (
    <div className="register">
      <div className="simple-register-container">
        <h1>Register</h1>
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col-md-12 form-group">
              <input
                name="fullName"
                type="text"
                className="form-control"
                placeholder="Fullname"
                onChange={onChange}
                required
              />
            </div>
            <div className="col-md-12 form-group">
              <input
                name="email"
                type="email"
                className={classnames("form-control", {
                  "is-invalid": errors.message
                })}
                placeholder="Email"
                onChange={onChange}
                required
              />
            </div>
            {errors.message && (
              <div className="text-danger px-3">{errors.message}</div>
            )}
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <input
                name="password"
                type="password"
                placeholder="Password"
                className="form-control"
                onChange={onChange}
                required
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <div className="input-group">
                <input
                  placeholder="Confirm Password"
                  type="password"
                  className="form-control"
                  name="confirmPassword"
                  value={confirmPassword}
                  onChange={e => {
                    validatePassword(e);
                    setConfirmPassword(e.target.value);
                  }}
                  onBlur={validatePassword}
                  title="Re-type password to confirm."
                  required
                />
                {message.isSet && (
                  <div className="input-group-append">
                    {message.matches ? (
                      <span className={`input-group-text ${message.css}`}>
                        <i className="fa fa-check" aria-hidden="true"></i>
                      </span>
                    ) : (
                      <span className={`input-group-text ${message.css}`}>
                        <i class="fa fa-times" aria-hidden="true"></i>
                      </span>
                    )}
                  </div>
                )}
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <button
                type="submit"
                className="btn btn-block btn-register"
                disabled={!message.matches}
              >
                Register
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;
