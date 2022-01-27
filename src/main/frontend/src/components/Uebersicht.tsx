import React, { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axios from "axios";
import { styled } from "@mui/material/styles";

import { Eiscremesorte } from "../types/Eiscremesorte";
import { Box, Button, Grid } from "@mui/material";
import { useHistory } from "react-router-dom";

const Uebersicht = () => {
  const [fetchedEiscremesorten, setFetchedEiscremesorten] = useState([]);

  const history = useHistory();

  useEffect(() => {
    const fetchEiscremesorten = async () => {
      try {
        const resp = await axios.get(
          "http://localhost:8080/eiscrememanager/eiscremesorten"
        );
        setFetchedEiscremesorten(resp.data);
      } catch (err) {
        // Handle Error Here
        console.error(err);
      }
    };
    fetchEiscremesorten();
  }, []);

  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: "dodgerblue",
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));

  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));

  return (
    fetchedEiscremesorten && (
      <div>
        
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell>Eiscremesorte</StyledTableCell>
                <StyledTableCell align="right">Kategorie</StyledTableCell>
                <StyledTableCell align="right">Zutaten</StyledTableCell>
                <StyledTableCell align="right">Nährwert kcal</StyledTableCell>
                <StyledTableCell align="right">Verkaufspreis €</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {fetchedEiscremesorten.map((row: Eiscremesorte) => (
                <StyledTableRow key={row.name}>
                  <StyledTableCell component="th" scope="row">
                    {row.name}
                  </StyledTableCell>
                  <StyledTableCell align="right">
                    {row.kategorie}
                  </StyledTableCell>
                  <StyledTableCell align="right">
                    {row.zutaten.join(", ")}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.kcal}</StyledTableCell>
                  <StyledTableCell align="right">{row.preis}</StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
          <Button
            variant="contained"
            type="button"
            style={{ backgroundColor: "dodgerblue" }}
            onClick={() => history.push("/anlegen")}
          >
            Zurück zur Anlage
          </Button>
        </Box>
      </div>
    )
  );
};

export default Uebersicht;
