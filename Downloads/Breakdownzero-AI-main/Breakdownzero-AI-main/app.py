from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
import joblib
import pandas as pd
import os

app = FastAPI(title="Predictive Maintenance RUL API Marketplace")

class MachineData(BaseModel):
    sensor_readings: List[float]

class RULPredictor:
    def __init__(self):
        model_path = os.path.join(os.path.dirname(__file__), "models", "rul_model.pkl")
        self.model = joblib.load(model_path)
        
        # EXACT column names used during training (from your notebook)
        self.feature_cols = [f'op_setting_{i}' for i in range(1,4)] + \
                            [f'sensor_{i}' for i in range(1,22)]

    def predict(self, data: list):
        df = pd.DataFrame([data], columns=self.feature_cols)
        return float(self.model.predict(df)[0])

@app.get("/")
def home():
    return {"status": "API Running Successfully", "docs": "/docs"}

@app.post("/predict")
def predict_rul(data: MachineData):
    try:
        predictor = RULPredictor()
        if len(data.sensor_readings) != 24:
            raise HTTPException(400, "Exactly 24 values required")
        
        rul = predictor.predict(data.sensor_readings)
        
        return {
            "remaining_useful_life": round(rul, 2),
            "unit": "cycles",
            "risk_level": "CRITICAL" if rul < 30 else "WARNING" if rul < 100 else "HEALTHY"
        }
    except Exception as e:
        raise HTTPException(500, f"Prediction failed: {str(e)}")