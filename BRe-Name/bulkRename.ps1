# Get the folder path from user input
$FolderPath = Read-Host "Enter folder path:"

# Get the prefix from user input
$Prefix = Read-Host "Enter prefix for new file names (press Enter for no prefix):"

# Get all files in the folder and its subfolders
$Files = Get-ChildItem $FolderPath -Recurse -File

# Loop through each file and rename it
foreach ($File in $Files) {
    # Get the file extension
    $Extension = $File.Extension
    
    # Generate a new file name using the current date and time
    $NewName = Get-Date -Format "yyyyMMdd_HHmmss"
    
    # Add the prefix to the new file name, if one was specified
    if ($Prefix) {
        $NewName = "$Prefix" + "_" + $NewName
    }
    
    # Add the file extension to the new file name
    $NewName += $Extension
    
    # Rename the file
    Rename-Item $File.FullName -NewName $NewName
}

# Display a message indicating the operation is complete
Write-Host "All files in '$FolderPath' have been renamed."
