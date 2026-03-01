from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
import os
import sys

# Fix paths
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from predict import RULPredictor  # Import the class (not instantiate yet)

app = FastAPI(title="Predictive Maintenance API Marketplace")

class MachineData(BaseModel):
    sensor_readings: List[float]

@app.get("/")
def home():
    return {"status": "Online", "model": "RUL-NASA-FD001", "try": "/docs"}

@app.post("/predict")
def get_prediction(data: MachineData):
    try:
        # Lazy load predictor (only when called)
        predictor = RULPredictor()
        
        if len(data.sensor_readings) != 24:
            raise HTTPException(status_code=400, detail="24 values required")
        
        result = predictor.predict(data.sensor_readings)
        
        return {
            "remaining_useful_life": round(float(result), 2),
            "cycles_left": "few" if result < 50 else "many",
            "risk": "HIGH" if result < 30 else "LOW"
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error: {str(e)}")