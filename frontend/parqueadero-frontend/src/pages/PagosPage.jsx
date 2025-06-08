import PagosForm from "../components/PagosForm";
import PagosList from "../components/PagosList";
import { useState } from "react";

function PagosPage() {
  const [refresh, setRefresh] = useState(false);

  return (
    <div className="pagos-page">
      <PagosForm onPagoRegistrado={() => setRefresh(!refresh)} />
      <PagosList key={refresh} />
    </div>
  );
}

export default PagosPage;

