cd /d %~dp0
echo Downloading the required libraries...
python -m pip install pyproj numpy shapely rtree pillow requests pyinstaller pynput==1.6.8 cpython
pyinstaller "../../../Ortho4XP/Ortho4XP_v130.py" -p "../../../Ortho4XP" -w --uac-uiaccess 