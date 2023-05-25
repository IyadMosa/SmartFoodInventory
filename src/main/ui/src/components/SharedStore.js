import React, { useEffect, useState } from "react";
import { Button, TableScreen, TextField } from "@iyadmosa/react-library";
import { useDispatch, useSelector } from "react-redux";
import {
  requestSharedItem,
  getSharedStoreItemsAll,
} from "../actions/itemAction";
import IconButton from "@material-ui/core/IconButton";
import RequestQuoteIcon from "@mui/icons-material/RequestQuote";
import styled from "styled-components";

const Buttons = styled.div`
  display: flex;
  gap: 20px;
`;
const SharedStore = () => {
  const [radius, setRadius] = useState(1);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getSharedStoreItemsAll(radius));
  }, []);
  const data = useSelector((state) => state.items.sharedItems);
  const columns = [
    {
      Header: "id",
      accessor: "id",
    },
    {
      Header: "consumed",
      accessor: "consumed",
    },
    {
      Header: "quantity",
      accessor: "quantity",
    },
    {
      Header: "sharingDate",
      accessor: "sharingDate",
    },
    {
      Header: "shared item name",
      accessor: "item.name",
    },
    {
      Header: "shared item id",
      accessor: "item.id",
    },
    {
      Header: "shared item expirationDate",
      accessor: "item.expirationDate",
    },
    {
      Header: "",
      filterable: false,
      sortable: false,
      resizable: false,
      Cell: (porps) => {
        return (
          <div>
            <IconButton
              color="primary"
              onClick={() => {
                dispatch(requestSharedItem(porps.original.id));
              }}
            >
              <RequestQuoteIcon titleAccess={"request item"} />
            </IconButton>
          </div>
        );
      },
      width: 100,
    },
  ];
  return (
    <di>
      <Buttons>
        <Button
          onClick={() => {
            setRadius(1);
            dispatch(getSharedStoreItemsAll(1));
          }}
          label={"1 Km"}
        />
        <Button
          onClick={() => {
            setRadius(2);
            dispatch(getSharedStoreItemsAll(2));
          }}
          label={"2 Km"}
        />
        <Button
          onClick={() => {
            setRadius(5);
            dispatch(getSharedStoreItemsAll(5));
          }}
          label={"5 Km"}
        />
        <Button
          onClick={() => {
            setRadius(10);
            dispatch(getSharedStoreItemsAll(10));
          }}
          label={"10 Km"}
        />
        <Button
          onClick={() => {
            setRadius(100);
            dispatch(getSharedStoreItemsAll(100));
          }}
          label={"100 Km"}
        />
        <Button
          onClick={() => {
            setRadius(500);
            dispatch(getSharedStoreItemsAll(500));
          }}
          label={"500 Km"}
        />
      </Buttons>
      <TableScreen
        title={"Shared Store"}
        showAdd={false}
        data={data}
        columns={columns}
        onInit={() => dispatch(getSharedStoreItemsAll(radius))}
      />
    </di>
  );
};

export default SharedStore;
