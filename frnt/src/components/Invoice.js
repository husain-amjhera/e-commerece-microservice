import React, { useEffect } from "react";
import { useParams } from "react-router";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { getInvoice } from "../actions/productActions";
import {
  Table,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Paper,
  Grid,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Typography
} from "@material-ui/core";
import LocationOnIcon from "@material-ui/icons/LocationOn";
import "./Invoice.css";
function Invoice() {
  const dispatch = useDispatch();
  const { orderId } = useParams();
  useEffect(() => {
    dispatch(getInvoice(orderId));
  }, [orderId]);
  const invoice = useSelector(state => state.invoice);
  const address = invoice.address;
  return (
    <div className="invoice">
      <div style={{ marginTop: "5%" }}>
        <h1>Invoice</h1>
        <hr />
      </div>
      <TableContainer style={{ overflowX: "hidden" }} component={Paper}>
        <div
          className="row"
          style={{
            display: "flex",
            flexDirection: "row",
            alignContent: "space-between"
          }}
        >
          <div className="ml-2 mt-2 col">
            <h5>
              Invoice to:{" "}
              <Typography style={{ display: "inline-block" }}>
                {" "}
                {invoice.invoiceTo}
              </Typography>
            </h5>
          </div>
          <div className="ml-2 mt-2 col" style={{ textAlign: "center" }}>
            <h5>
              Invoice from:{" "}
              <Typography style={{ display: "inline-block" }}>
                {" "}
                Ecommerce Pvt. Ltd.
              </Typography>
            </h5>
          </div>
          <div className="mt-2 mr-2 col" style={{ textAlign: "right" }}>
            <h5>
              Date:{" "}
              <Typography style={{ display: "inline-block" }}>
                {" "}
                {invoice.date}
              </Typography>
            </h5>
          </div>
        </div>
        <Grid
          container
          direction="column"
          justify="flex-start"
          alignItems="flex-start"
          xs={12}
        >
          <List>
            <h5 className="ml-2 mt-2">Address:</h5>
            <ListItem>
              <ListItemIcon>
                <LocationOnIcon />
              </ListItemIcon>
              <ListItemText
                primary={address?.address1 + " " + address?.address2}
                secondary={
                  address?.city + "," + address?.state + "," + address?.zip
                }
              />
            </ListItem>
          </List>
        </Grid>
        <Grid>
          <Table>
            <TableHead style={{ backgroundColor: "#f0e0b6" }}>
              <TableRow>
                <TableCell className="heading">Title</TableCell>
                <TableCell className="heading" align="right">
                  Quantity
                </TableCell>
                <TableCell className="heading" align="right">
                  Price
                </TableCell>
                <TableCell className="heading" align="right">
                  Tax
                </TableCell>
                <TableCell className="heading" align="right">
                  Tax Amount
                </TableCell>
                <TableCell className="heading" align="right">
                  Total
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody className="table__body">
              {invoice?.invoiceItem?.map(row => (
                <TableRow key={row.title}>
                  <TableCell component="th" scope="row">
                    {row.title}
                  </TableCell>
                  <TableCell align="right">{row.quantity}</TableCell>
                  <TableCell align="right">₹&nbsp;{row.price}</TableCell>
                  <TableCell align="right">{row.tax}&nbsp;%</TableCell>
                  <TableCell align="right">₹&nbsp;{row.taxAmount}</TableCell>
                  <TableCell align="right">₹&nbsp;{row.subTotal}</TableCell>
                </TableRow>
              ))}
              <TableRow>
                <TableCell colSpan={5} align="right">
                  <strong>Shipping Charges</strong>
                </TableCell>
                <TableCell align="right">
                  ₹&nbsp;{invoice.shippingCharges}
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell colSpan={5} align="right">
                  <strong>Grand Total</strong>
                </TableCell>
                <TableCell align="right">₹&nbsp;{invoice.total}</TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </Grid>
      </TableContainer>
    </div>
  );
}

export default Invoice;
