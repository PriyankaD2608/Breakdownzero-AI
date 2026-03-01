import joblib
import pandas as pd
import os

class RULPredictor:
    def __init__(self):
        # Robust path to model (works from backend/)
        base_path = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
        model_path = os.path.join(base_path, 'models', 'rul_model.pkl')
        
        print(f"Loading model from: {model_path}")  # Debug print
        
        if not os.path.exists(model_path):
            raise FileNotFoundError(f"Model missing! Put rul_model.pkl in models/ folder. Path: {model_path}")
        
        self.model = joblib.load(model_path)
        
        # EXACT columns from NASA FD001 training (setting_1-3 + s_1-21)
        self.feature_cols = (
            ['setting_1', 'setting_2', 'setting_3'] + 
            [f's_{i}' for i in range(1, 22)]
        )

    def predict(self, input_data: list):
        df = pd.DataFrame([input_data], columns=self.feature_cols)
        prediction = self.model.predict(df)
        return float(prediction[0])