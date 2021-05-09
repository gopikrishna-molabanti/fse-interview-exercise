import React from "react";
import View from "../../DesignSystem/View";
import EligibilityApplication from "./EligibilityApplication";
import EligibilityResults from "./EligibilityResults";

const Eligibility = () => {
  return (
    <View>
      <EligibilityApplication />
      <EligibilityResults />
    </View>
  );
};

export default Eligibility;
