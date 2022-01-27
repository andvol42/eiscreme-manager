import {
  Alert,
  Box,
  Button,
  Chip,
  Grid,
  InputLabel,
  MenuItem,
  OutlinedInput,
  Select,
  SelectChangeEvent,
  Snackbar,
  TextField,
  Theme,
  useTheme,
} from "@mui/material";
import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import { Kategorie, Kategorien } from "../types/Eiscremekategorien";

const Anlegen = () => {
  const {
    register,
    getValues,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const [kategorien, setKategorien] = useState([]);
  const [selectedKategorie, setSelectedKategorie] = useState("");
  const theme = useTheme();
  const [selectedZutat, setSelectedZutat] = useState<string[]>([]);
  const [open, setOpen] = React.useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const ITEM_HEIGHT = 48;
  const ITEM_PADDING_TOP = 8;

  const MenuProps = {
    PaperProps: {
      style: {
        maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
        width: 250,
      },
    },
  };

  const names = [
    "Milch",
    "Kokosfett",
    "Zucker",
    "Schlagsahne",
    "Bourbon-Vanilleextrakt",
    "Vanilleschoten",
    "Kakao",
  ];

  useEffect(() => {
    const fetchKategorien = async () => {
      const resp = await axios
        .get("http://localhost:8080/eiscrememanager/config/kategorien")
        .then((resp) => {
          setKategorien(resp.data);
        })
        .catch((error) => {
          // Handle Error Here
          console.error(error);

          if (error.response.status >= 500) {
            setErrorMessage("Es ist ein Fehler im System aufgetreten. Kategorien konnten nicht geladen werden");
            handleClick();
          } else {
            console.log("Error", error.message);
            setErrorMessage("Es ist ein unbekannter Fehler aufgetreten.");
            handleClick();
          }
          console.log(error.config);
        });
    };
    fetchKategorien();
  }, []);

  const history = useHistory();

  function getStyles(
    name: string,
    zutatenName: readonly string[],
    theme: Theme
  ) {
    return {
      fontWeight:
      zutatenName.indexOf(name) === -1
          ? theme.typography.fontWeightRegular
          : theme.typography.fontWeightMedium,
    };
  }

  const handleChange = (event: SelectChangeEvent<typeof selectedZutat>) => {
    const {
      target: { value },
    } = event;
    setSelectedZutat(
      // On autofill we get a stringified value.
      typeof value === "string" ? value.split(",") : value
    );
  };

  const handleKategorieChange = (event: SelectChangeEvent) => {
    setSelectedKategorie(event.target.value as string);
  };

  const handleClick = () => {
    setOpen(true);
  };

  const handleClose = (
    event: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  const onSubmit = async (data?: any) => {
    const eiscremeDaten = getValues();
    const zutatenAuswahl = selectedZutat;
    const kategorieWahl = selectedKategorie;

    const payload = {
      name: eiscremeDaten.eiscremename,
      kategorie: kategorieWahl,
      zutaten: zutatenAuswahl,
      kcal: eiscremeDaten.naehrwert,
      preis: eiscremeDaten.vkPreis,
    };

    await axios
      .post("http://localhost:8080/eiscrememanager/eiscremesorten/", payload)
      .then(() => {
        history.push("/uebersicht");
      })
      .catch((error) => {
        if (error.response) {
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);

          if (error.response.status === 409) {
            setErrorMessage(
              "Eiscremesorte konnte nicht angelegt werden, da bereits eine Sorte mit gleichem Namen oder Zutaten vorhanden ist."
            );
            handleClick();
          } else if (error.response.status >= 500) {
            setErrorMessage("Es ist ein Fehler im System aufgetreten.");
            handleClick();
          }
        } else {
          console.log("Error", error.message);
          setErrorMessage("Es ist ein unbekannter Fehler aufgetreten.");
          handleClick();
        }
        console.log(error.config);
      });
  };

  console.log(errors);

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <TextField
            id="eiscremename"
            label="Eiscremename"
            variant="outlined"
            style={{ width: "400px" }}
            {...register("eiscremename", { required: true, maxLength: 80 })}
          />
        </Grid>

        <Grid item xs={12}>
          <InputLabel id="kategorie-form-control">Kategorie</InputLabel>
          <Select
            labelId="kategorie-select-label"
            id="kategorie-select"
            value={selectedKategorie}
            label="Kategorie"
            onChange={handleKategorieChange}
            style={{ width: "400px" }}
          >
            {kategorien.map((k: Kategorie) => (
              <MenuItem key={k.label} value={k.wert}>
                {k.wert}
              </MenuItem>
            ))}
          </Select>
        </Grid>

        <Grid item xs={12}>
          <InputLabel id="zutaten-chip-label">Zutaten</InputLabel>
          <Select
            labelId="zutaten-label"
            id="zutaten-chip"
            multiple
            value={selectedZutat}
            onChange={handleChange}
            style={{ width: "400px" }}
            input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
            renderValue={(selected) => (
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                {selected.map((value) => (
                  <Chip key={value} label={value} />
                ))}
              </Box>
            )}
            MenuProps={MenuProps}
          >
            {names.map((name) => (
              <MenuItem
                key={name}
                value={name}
                style={getStyles(name, selectedZutat, theme)}
              >
                {name}
              </MenuItem>
            ))}
          </Select>
        </Grid>
        <Grid item xs={12}>
          <TextField
            id="lebensmittelvertraeglichkeit"
            label="Lebensmittelverträglichkeit"
            variant="outlined"
            multiline
            rows={4}
            style={{ width: "400px" }}
            {...register("lebensmittelvertraeglichkeit", {
              required: true,
              maxLength: 80,
            })}
          />
        </Grid>

        <Grid item xs={12}>
          <TextField
            id="naehwert"
            label="Nährwert"
            variant="outlined"
            style={{ width: "400px" }}
            {...register("naehrwert", { required: true, maxLength: 80 })}
          />
        </Grid>

        <Grid item xs={12}>
          <TextField
            id="vkPreis"
            label="Verkaufspreis"
            variant="outlined"
            style={{ width: "400px" }}
            {...register("vkPreis", { required: true, maxLength: 80 })}
          />
        </Grid>

        <Grid item xs={12}>
          <Button
            variant="contained"
            type="submit"
            style={{ backgroundColor: "dodgerblue" }}
          >
            Anlegen
          </Button>
          <Button
            variant="contained"
            type="button"
            style={{ backgroundColor: "dodgerblue" }}
            onClick={() => history.push("/uebersicht")}
            sx={{ ml: 2 }}
          >
            Zur Übersicht
          </Button>
        </Grid>
      </Grid>
      <Snackbar
        open={open}
        autoHideDuration={6000}
        onClose={handleClose}
        message="Note archived"
      >
        <Alert severity="error">{errorMessage}</Alert>
      </Snackbar>
    </form>
  );
};

export default Anlegen;
