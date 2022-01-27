import { Container, Typography } from "@mui/material";
import Anlegen from "./components/Anlegen";
import Uebersicht from "./components/Uebersicht";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";

function App() {
  return (
    <Router>
      <div>
        <Container fixed>
          <Switch>
            <Route exact path="/">
              <Redirect to="/anlegen" />
            </Route>
            <Route path="/anlegen" exact>
              <Typography variant="h4" gutterBottom sx={{ mt: 4 }}>
                Eiscremesorte anlegen
              </Typography>
              <Anlegen />
            </Route>
            <Route path="/uebersicht" exact>
              <Typography variant="h4" gutterBottom sx={{ mt: 4 }}>
                Übersicht Eiscremesorten
              </Typography>
              <Uebersicht />
            </Route>
          </Switch>
        </Container>
      </div>
    </Router>
  );
}

export default App;
