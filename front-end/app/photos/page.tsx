'use client';

import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { usePhotos } from '@/features/photos/hooks/usePhotos';
import { Card, CardContent, CardFooter } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { Trash2, Image as ImageIcon } from 'lucide-react';

export default function PhotosPage() {
  const { photos, loading, error, deletePhoto } = usePhotos();
  const { showToast, ToastComponent } = useToast();

  const handleDelete = async (id: number) => {
    try {
      await deletePhoto(id);
      showToast({ message: 'Photo deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete photo', type: 'error' });
    }
  };

  if (loading && photos.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-slate-900">Photos</h1>
          <p className="text-slate-500 mt-1">Browse and manage photos</p>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {photos.map((photo) => (
            <Card key={photo.id} className="overflow-hidden hover:shadow-md transition-shadow">
              <CardContent className="p-0">
                <div className="aspect-square bg-slate-100 flex items-center justify-center">
                  <ImageIcon className="h-12 w-12 text-slate-400" />
                  {/* In production, you would show the actual image */}
                  {/* <img src={photo.url} alt={photo.title} className="w-full h-full object-cover" /> */}
                </div>
              </CardContent>
              <CardFooter className="flex-col items-start p-3 gap-2">
                <p className="text-sm font-medium text-slate-900 line-clamp-2">{photo.title}</p>
                <div className="flex items-center justify-between w-full">
                  <p className="text-xs text-slate-500">Album: {photo.album?.id || 'N/A'}</p>
                  <Button variant="danger" size="sm" onClick={() => handleDelete(photo.id)}>
                    <Trash2 className="h-3 w-3" />
                  </Button>
                </div>
              </CardFooter>
            </Card>
          ))}
        </div>

        {photos.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No photos found.</p>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
